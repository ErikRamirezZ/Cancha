package com.raze.cancha.controller;
import com.raze.cancha.domain.Empresa;
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

@RooWebJson(jsonObject = Sucursal.class)
@Controller
@RequestMapping("/sucursals")
@RooWebScaffold(path = "sucursals", formBackingObject = Sucursal.class)
@RooWebFinder
public class SucursalController {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> showJson(@PathVariable("id") Long id) {
        Sucursal sucursal = Sucursal.findSucursal(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        if (sucursal == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(sucursal.toJson(), headers, HttpStatus.OK);
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<Sucursal> result = Sucursal.findAllSucursals();
        return new ResponseEntity<String>(Sucursal.toJsonArray(result), headers, HttpStatus.OK);
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody String json, UriComponentsBuilder uriBuilder) {
        Sucursal sucursal = Sucursal.fromJsonToSucursal(json);
        sucursal.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        RequestMapping a = (RequestMapping) getClass().getAnnotation(RequestMapping.class);
        headers.add("Location",uriBuilder.path(a.value()[0]+"/"+sucursal.getId().toString()).build().toUriString());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/jsonArray", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJsonArray(@RequestBody String json) {
        for (Sucursal sucursal: Sucursal.fromJsonArrayToSucursals(json)) {
            sucursal.persist();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody String json, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        Sucursal sucursal = Sucursal.fromJsonToSucursal(json);
        sucursal.setId(id);
        if (sucursal.merge() == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        Sucursal sucursal = Sucursal.findSucursal(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (sucursal == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        sucursal.remove();
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByIdEmpresaAndStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindSucursalsByIdEmpresaAndStatus(@RequestParam("idEmpresa") Empresa idEmpresa, @RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Sucursal.toJsonArray(Sucursal.findSucursalsByIdEmpresaAndStatus(idEmpresa, status).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindSucursalsByStatus(@RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Sucursal.toJsonArray(Sucursal.findSucursalsByStatus(status).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Sucursal sucursal, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, sucursal);
            return "sucursals/create";
        }
        uiModel.asMap().clear();
        sucursal.persist();
        return "redirect:/sucursals/" + encodeUrlPathSegment(sucursal.getId().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Sucursal());
        return "sucursals/create";
    }

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("sucursal", Sucursal.findSucursal(id));
        uiModel.addAttribute("itemId", id);
        return "sucursals/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("sucursals", Sucursal.findSucursalEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Sucursal.countSucursals() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("sucursals", Sucursal.findAllSucursals(sortFieldName, sortOrder));
        }
        addDateTimeFormatPatterns(uiModel);
        return "sucursals/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Sucursal sucursal, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, sucursal);
            return "sucursals/update";
        }
        uiModel.asMap().clear();
        sucursal.merge();
        return "redirect:/sucursals/" + encodeUrlPathSegment(sucursal.getId().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, Sucursal.findSucursal(id));
        return "sucursals/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Sucursal sucursal = Sucursal.findSucursal(id);
        sucursal.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/sucursals";
    }

	void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("sucursal_fechacreacion_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("sucursal_fechamodificacion_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }

	void populateEditForm(Model uiModel, Sucursal sucursal) {
        uiModel.addAttribute("sucursal", sucursal);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("empresas", Empresa.findAllEmpresas());
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

	@RequestMapping(params = { "find=ByIdEmpresaAndStatus", "form" }, method = RequestMethod.GET)
    public String findSucursalsByIdEmpresaAndStatusForm(Model uiModel) {
        uiModel.addAttribute("empresas", Empresa.findAllEmpresas());
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        return "sucursals/findSucursalsByIdEmpresaAndStatus";
    }

	@RequestMapping(params = "find=ByIdEmpresaAndStatus", method = RequestMethod.GET)
    public String findSucursalsByIdEmpresaAndStatus(@RequestParam("idEmpresa") Empresa idEmpresa, @RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("sucursals", Sucursal.findSucursalsByIdEmpresaAndStatus(idEmpresa, status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Sucursal.countFindSucursalsByIdEmpresaAndStatus(idEmpresa, status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("sucursals", Sucursal.findSucursalsByIdEmpresaAndStatus(idEmpresa, status, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "sucursals/list";
    }

	@RequestMapping(params = { "find=ByStatus", "form" }, method = RequestMethod.GET)
    public String findSucursalsByStatusForm(Model uiModel) {
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        return "sucursals/findSucursalsByStatus";
    }

	@RequestMapping(params = "find=ByStatus", method = RequestMethod.GET)
    public String findSucursalsByStatus(@RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("sucursals", Sucursal.findSucursalsByStatus(status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Sucursal.countFindSucursalsByStatus(status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("sucursals", Sucursal.findSucursalsByStatus(status, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "sucursals/list";
    }
}
