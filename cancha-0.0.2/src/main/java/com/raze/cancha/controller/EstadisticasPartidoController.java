package com.raze.cancha.controller;
import com.raze.cancha.domain.EstadisticasPartido;
import com.raze.cancha.domain.Partido;
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

@RooWebJson(jsonObject = EstadisticasPartido.class)
@Controller
@RequestMapping("/estadisticaspartidoes")
@RooWebScaffold(path = "estadisticaspartidoes", formBackingObject = EstadisticasPartido.class)
@RooWebFinder
public class EstadisticasPartidoController {

	@RequestMapping(params = { "find=ByIdPartido", "form" }, method = RequestMethod.GET)
    public String findEstadisticasPartidoesByIdPartidoForm(Model uiModel) {
        uiModel.addAttribute("partidoes", Partido.findAllPartidoes());
        return "estadisticaspartidoes/findEstadisticasPartidoesByIdPartido";
    }

	@RequestMapping(params = "find=ByIdPartido", method = RequestMethod.GET)
    public String findEstadisticasPartidoesByIdPartido(@RequestParam("idPartido") Partido idPartido, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("estadisticaspartidoes", EstadisticasPartido.findEstadisticasPartidoesByIdPartido(idPartido, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) EstadisticasPartido.countFindEstadisticasPartidoesByIdPartido(idPartido) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("estadisticaspartidoes", EstadisticasPartido.findEstadisticasPartidoesByIdPartido(idPartido, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "estadisticaspartidoes/list";
    }

	@RequestMapping(params = { "find=ByIdPartidoAndStatus", "form" }, method = RequestMethod.GET)
    public String findEstadisticasPartidoesByIdPartidoAndStatusForm(Model uiModel) {
        uiModel.addAttribute("partidoes", Partido.findAllPartidoes());
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        return "estadisticaspartidoes/findEstadisticasPartidoesByIdPartidoAndStatus";
    }

	@RequestMapping(params = "find=ByIdPartidoAndStatus", method = RequestMethod.GET)
    public String findEstadisticasPartidoesByIdPartidoAndStatus(@RequestParam("idPartido") Partido idPartido, @RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("estadisticaspartidoes", EstadisticasPartido.findEstadisticasPartidoesByIdPartidoAndStatus(idPartido, status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) EstadisticasPartido.countFindEstadisticasPartidoesByIdPartidoAndStatus(idPartido, status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("estadisticaspartidoes", EstadisticasPartido.findEstadisticasPartidoesByIdPartidoAndStatus(idPartido, status, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "estadisticaspartidoes/list";
    }

	@RequestMapping(params = { "find=ByStatus", "form" }, method = RequestMethod.GET)
    public String findEstadisticasPartidoesByStatusForm(Model uiModel) {
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        return "estadisticaspartidoes/findEstadisticasPartidoesByStatus";
    }

	@RequestMapping(params = "find=ByStatus", method = RequestMethod.GET)
    public String findEstadisticasPartidoesByStatus(@RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("estadisticaspartidoes", EstadisticasPartido.findEstadisticasPartidoesByStatus(status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) EstadisticasPartido.countFindEstadisticasPartidoesByStatus(status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("estadisticaspartidoes", EstadisticasPartido.findEstadisticasPartidoesByStatus(status, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "estadisticaspartidoes/list";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> showJson(@PathVariable("id") Long id) {
        EstadisticasPartido estadisticasPartido = EstadisticasPartido.findEstadisticasPartido(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        if (estadisticasPartido == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(estadisticasPartido.toJson(), headers, HttpStatus.OK);
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<EstadisticasPartido> result = EstadisticasPartido.findAllEstadisticasPartidoes();
        return new ResponseEntity<String>(EstadisticasPartido.toJsonArray(result), headers, HttpStatus.OK);
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody String json, UriComponentsBuilder uriBuilder) {
        EstadisticasPartido estadisticasPartido = EstadisticasPartido.fromJsonToEstadisticasPartido(json);
        estadisticasPartido.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        RequestMapping a = (RequestMapping) getClass().getAnnotation(RequestMapping.class);
        headers.add("Location",uriBuilder.path(a.value()[0]+"/"+estadisticasPartido.getId().toString()).build().toUriString());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/jsonArray", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJsonArray(@RequestBody String json) {
        for (EstadisticasPartido estadisticasPartido: EstadisticasPartido.fromJsonArrayToEstadisticasPartidoes(json)) {
            estadisticasPartido.persist();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody String json, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        EstadisticasPartido estadisticasPartido = EstadisticasPartido.fromJsonToEstadisticasPartido(json);
        estadisticasPartido.setId(id);
        if (estadisticasPartido.merge() == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        EstadisticasPartido estadisticasPartido = EstadisticasPartido.findEstadisticasPartido(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (estadisticasPartido == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        estadisticasPartido.remove();
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByIdPartido", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindEstadisticasPartidoesByIdPartido(@RequestParam("idPartido") Partido idPartido) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(EstadisticasPartido.toJsonArray(EstadisticasPartido.findEstadisticasPartidoesByIdPartido(idPartido).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByIdPartidoAndStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindEstadisticasPartidoesByIdPartidoAndStatus(@RequestParam("idPartido") Partido idPartido, @RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(EstadisticasPartido.toJsonArray(EstadisticasPartido.findEstadisticasPartidoesByIdPartidoAndStatus(idPartido, status).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindEstadisticasPartidoesByStatus(@RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(EstadisticasPartido.toJsonArray(EstadisticasPartido.findEstadisticasPartidoesByStatus(status).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid EstadisticasPartido estadisticasPartido, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, estadisticasPartido);
            return "estadisticaspartidoes/create";
        }
        uiModel.asMap().clear();
        estadisticasPartido.persist();
        return "redirect:/estadisticaspartidoes/" + encodeUrlPathSegment(estadisticasPartido.getId().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new EstadisticasPartido());
        return "estadisticaspartidoes/create";
    }

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("estadisticaspartido", EstadisticasPartido.findEstadisticasPartido(id));
        uiModel.addAttribute("itemId", id);
        return "estadisticaspartidoes/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("estadisticaspartidoes", EstadisticasPartido.findEstadisticasPartidoEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) EstadisticasPartido.countEstadisticasPartidoes() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("estadisticaspartidoes", EstadisticasPartido.findAllEstadisticasPartidoes(sortFieldName, sortOrder));
        }
        addDateTimeFormatPatterns(uiModel);
        return "estadisticaspartidoes/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid EstadisticasPartido estadisticasPartido, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, estadisticasPartido);
            return "estadisticaspartidoes/update";
        }
        uiModel.asMap().clear();
        estadisticasPartido.merge();
        return "redirect:/estadisticaspartidoes/" + encodeUrlPathSegment(estadisticasPartido.getId().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, EstadisticasPartido.findEstadisticasPartido(id));
        return "estadisticaspartidoes/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        EstadisticasPartido estadisticasPartido = EstadisticasPartido.findEstadisticasPartido(id);
        estadisticasPartido.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/estadisticaspartidoes";
    }

	void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("estadisticasPartido_fechacreacion_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("estadisticasPartido_fechamodificacion_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }

	void populateEditForm(Model uiModel, EstadisticasPartido estadisticasPartido) {
        uiModel.addAttribute("estadisticasPartido", estadisticasPartido);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("partidoes", Partido.findAllPartidoes());
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
}
