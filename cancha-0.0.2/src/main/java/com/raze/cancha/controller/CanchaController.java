package com.raze.cancha.controller;
import com.raze.cancha.domain.Cancha;
import com.raze.cancha.domain.Sucursal;
import com.raze.cancha.reference.Status;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.roo.addon.web.mvc.controller.finder.RooWebFinder;

@RooWebJson(jsonObject = Cancha.class)
@Controller
@RequestMapping("/canchas")
@RooWebScaffold(path = "canchas", formBackingObject = Cancha.class)
@RooWebFinder
public class CanchaController {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> showJson(@PathVariable("id") Long id) {
        Cancha cancha = Cancha.findCancha(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        if (cancha == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(cancha.toJson(), headers, HttpStatus.OK);
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<Cancha> result = Cancha.findAllCanchas();
        return new ResponseEntity<String>(Cancha.toJsonArray(result), headers, HttpStatus.OK);
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody String json, UriComponentsBuilder uriBuilder) {
        Cancha cancha = Cancha.fromJsonToCancha(json);
        cancha.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        RequestMapping a = (RequestMapping) getClass().getAnnotation(RequestMapping.class);
        headers.add("Location",uriBuilder.path(a.value()[0]+"/"+cancha.getId().toString()).build().toUriString());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/jsonArray", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJsonArray(@RequestBody String json) {
        for (Cancha cancha: Cancha.fromJsonArrayToCanchas(json)) {
            cancha.persist();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody String json, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        Cancha cancha = Cancha.fromJsonToCancha(json);
        cancha.setId(id);
        if (cancha.merge() == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        Cancha cancha = Cancha.findCancha(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (cancha == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        cancha.remove();
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByIdSucursalAndStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindCanchasByIdSucursalAndStatus(@RequestParam("idSucursal") Sucursal idSucursal, @RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Cancha.toJsonArray(Cancha.findCanchasByIdSucursalAndStatus(idSucursal, status).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindCanchasByStatus(@RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Cancha.toJsonArray(Cancha.findCanchasByStatus(status).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Cancha cancha, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, cancha);
            return "canchas/create";
        }
        uiModel.asMap().clear();
        cancha.persist();
        return "redirect:/canchas/" + encodeUrlPathSegment(cancha.getId().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Cancha());
        return "canchas/create";
    }

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("cancha", Cancha.findCancha(id));
        uiModel.addAttribute("itemId", id);
        return "canchas/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("canchas", Cancha.findCanchaEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Cancha.countCanchas() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("canchas", Cancha.findAllCanchas(sortFieldName, sortOrder));
        }
        addDateTimeFormatPatterns(uiModel);
        return "canchas/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Cancha cancha, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, cancha);
            return "canchas/update";
        }
        uiModel.asMap().clear();
        cancha.merge();
        return "redirect:/canchas/" + encodeUrlPathSegment(cancha.getId().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, Cancha.findCancha(id));
        return "canchas/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Cancha cancha = Cancha.findCancha(id);
        cancha.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/canchas";
    }

	void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("cancha_fechacreacion_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("cancha_fechamodificacion_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }

	void populateEditForm(Model uiModel, Cancha cancha) {
        uiModel.addAttribute("cancha", cancha);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("sucursals", Sucursal.findAllSucursals());
        uiModel.addAttribute("statuses", Status.findAllStatuses());
    }

	String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }

	@RequestMapping(params = { "find=ByIdSucursalAndStatus", "form" }, method = RequestMethod.GET)
    public String findCanchasByIdSucursalAndStatusForm(Model uiModel) {
        uiModel.addAttribute("sucursals", Sucursal.findAllSucursals());
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        return "canchas/findCanchasByIdSucursalAndStatus";
    }

	@RequestMapping(params = "find=ByIdSucursalAndStatus", method = RequestMethod.GET)
    public String findCanchasByIdSucursalAndStatus(@RequestParam("idSucursal") Sucursal idSucursal, @RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("canchas", Cancha.findCanchasByIdSucursalAndStatus(idSucursal, status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Cancha.countFindCanchasByIdSucursalAndStatus(idSucursal, status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("canchas", Cancha.findCanchasByIdSucursalAndStatus(idSucursal, status, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "canchas/list";
    }

	@RequestMapping(params = { "find=ByStatus", "form" }, method = RequestMethod.GET)
    public String findCanchasByStatusForm(Model uiModel) {
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        return "canchas/findCanchasByStatus";
    }

	@RequestMapping(params = "find=ByStatus", method = RequestMethod.GET)
    public String findCanchasByStatus(@RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("canchas", Cancha.findCanchasByStatus(status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Cancha.countFindCanchasByStatus(status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("canchas", Cancha.findCanchasByStatus(status, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "canchas/list";
    }
}
