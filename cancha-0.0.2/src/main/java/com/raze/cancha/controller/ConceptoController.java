package com.raze.cancha.controller;
import com.raze.cancha.reference.Concepto;
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

@RooWebJson(jsonObject = Concepto.class)
@Controller
@RequestMapping("/conceptoes")
@RooWebScaffold(path = "conceptoes", formBackingObject = Concepto.class)
@RooWebFinder
public class ConceptoController {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> showJson(@PathVariable("id") Long id) {
        Concepto concepto = Concepto.findConcepto(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        if (concepto == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(concepto.toJson(), headers, HttpStatus.OK);
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<Concepto> result = Concepto.findAllConceptoes();
        return new ResponseEntity<String>(Concepto.toJsonArray(result), headers, HttpStatus.OK);
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody String json, UriComponentsBuilder uriBuilder) {
        Concepto concepto = Concepto.fromJsonToConcepto(json);
        concepto.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        RequestMapping a = (RequestMapping) getClass().getAnnotation(RequestMapping.class);
        headers.add("Location",uriBuilder.path(a.value()[0]+"/"+concepto.getId().toString()).build().toUriString());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/jsonArray", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJsonArray(@RequestBody String json) {
        for (Concepto concepto: Concepto.fromJsonArrayToConceptoes(json)) {
            concepto.persist();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody String json, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        Concepto concepto = Concepto.fromJsonToConcepto(json);
        concepto.setId(id);
        if (concepto.merge() == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        Concepto concepto = Concepto.findConcepto(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (concepto == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        concepto.remove();
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByNombreLike", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindConceptoesByNombreLike(@RequestParam("nombre") String nombre) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Concepto.toJsonArray(Concepto.findConceptoesByNombreLike(nombre).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByNombreLikeAndStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindConceptoesByNombreLikeAndStatus(@RequestParam("nombre") String nombre, @RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Concepto.toJsonArray(Concepto.findConceptoesByNombreLikeAndStatus(nombre, status).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindConceptoesByStatus(@RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Concepto.toJsonArray(Concepto.findConceptoesByStatus(status).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Concepto concepto, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, concepto);
            return "conceptoes/create";
        }
        uiModel.asMap().clear();
        concepto.persist();
        return "redirect:/conceptoes/" + encodeUrlPathSegment(concepto.getId().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Concepto());
        return "conceptoes/create";
    }

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("concepto", Concepto.findConcepto(id));
        uiModel.addAttribute("itemId", id);
        return "conceptoes/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("conceptoes", Concepto.findConceptoEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Concepto.countConceptoes() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("conceptoes", Concepto.findAllConceptoes(sortFieldName, sortOrder));
        }
        addDateTimeFormatPatterns(uiModel);
        return "conceptoes/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Concepto concepto, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, concepto);
            return "conceptoes/update";
        }
        uiModel.asMap().clear();
        concepto.merge();
        return "redirect:/conceptoes/" + encodeUrlPathSegment(concepto.getId().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, Concepto.findConcepto(id));
        return "conceptoes/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Concepto concepto = Concepto.findConcepto(id);
        concepto.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/conceptoes";
    }

	void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("concepto_fechacreacion_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("concepto_fechamodificacion_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }

	void populateEditForm(Model uiModel, Concepto concepto) {
        uiModel.addAttribute("concepto", concepto);
        addDateTimeFormatPatterns(uiModel);
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

	@RequestMapping(params = { "find=ByNombreLike", "form" }, method = RequestMethod.GET)
    public String findConceptoesByNombreLikeForm(Model uiModel) {
        return "conceptoes/findConceptoesByNombreLike";
    }

	@RequestMapping(params = "find=ByNombreLike", method = RequestMethod.GET)
    public String findConceptoesByNombreLike(@RequestParam("nombre") String nombre, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("conceptoes", Concepto.findConceptoesByNombreLike(nombre, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Concepto.countFindConceptoesByNombreLike(nombre) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("conceptoes", Concepto.findConceptoesByNombreLike(nombre, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "conceptoes/list";
    }

	@RequestMapping(params = { "find=ByNombreLikeAndStatus", "form" }, method = RequestMethod.GET)
    public String findConceptoesByNombreLikeAndStatusForm(Model uiModel) {
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        return "conceptoes/findConceptoesByNombreLikeAndStatus";
    }

	@RequestMapping(params = "find=ByNombreLikeAndStatus", method = RequestMethod.GET)
    public String findConceptoesByNombreLikeAndStatus(@RequestParam("nombre") String nombre, @RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("conceptoes", Concepto.findConceptoesByNombreLikeAndStatus(nombre, status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Concepto.countFindConceptoesByNombreLikeAndStatus(nombre, status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("conceptoes", Concepto.findConceptoesByNombreLikeAndStatus(nombre, status, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "conceptoes/list";
    }

	@RequestMapping(params = { "find=ByStatus", "form" }, method = RequestMethod.GET)
    public String findConceptoesByStatusForm(Model uiModel) {
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        return "conceptoes/findConceptoesByStatus";
    }

	@RequestMapping(params = "find=ByStatus", method = RequestMethod.GET)
    public String findConceptoesByStatus(@RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("conceptoes", Concepto.findConceptoesByStatus(status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Concepto.countFindConceptoesByStatus(status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("conceptoes", Concepto.findConceptoesByStatus(status, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "conceptoes/list";
    }
}
