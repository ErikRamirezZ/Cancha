package com.raze.cancha.controller;
import com.raze.cancha.domain.Arbitro;
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

@RooWebJson(jsonObject = Arbitro.class)
@Controller
@RequestMapping("/arbitroes")
@RooWebScaffold(path = "arbitroes", formBackingObject = Arbitro.class)
@RooWebFinder
public class ArbitroController {

	@RequestMapping(params = { "find=ByCorreoEEquals", "form" }, method = RequestMethod.GET)
    public String findArbitroesByCorreoEEqualsForm(Model uiModel) {
        return "arbitroes/findArbitroesByCorreoEEquals";
    }

	@RequestMapping(params = "find=ByCorreoEEquals", method = RequestMethod.GET)
    public String findArbitroesByCorreoEEquals(@RequestParam("correoE") String correoE, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("arbitroes", Arbitro.findArbitroesByCorreoEEquals(correoE, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Arbitro.countFindArbitroesByCorreoEEquals(correoE) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("arbitroes", Arbitro.findArbitroesByCorreoEEquals(correoE, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "arbitroes/list";
    }

	@RequestMapping(params = { "find=ByCorreoEEqualsAndStatus", "form" }, method = RequestMethod.GET)
    public String findArbitroesByCorreoEEqualsAndStatusForm(Model uiModel) {
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        return "arbitroes/findArbitroesByCorreoEEqualsAndStatus";
    }

	@RequestMapping(params = "find=ByCorreoEEqualsAndStatus", method = RequestMethod.GET)
    public String findArbitroesByCorreoEEqualsAndStatus(@RequestParam("correoE") String correoE, @RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("arbitroes", Arbitro.findArbitroesByCorreoEEqualsAndStatus(correoE, status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Arbitro.countFindArbitroesByCorreoEEqualsAndStatus(correoE, status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("arbitroes", Arbitro.findArbitroesByCorreoEEqualsAndStatus(correoE, status, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "arbitroes/list";
    }

	@RequestMapping(params = { "find=ByCorreoELike", "form" }, method = RequestMethod.GET)
    public String findArbitroesByCorreoELikeForm(Model uiModel) {
        return "arbitroes/findArbitroesByCorreoELike";
    }

	@RequestMapping(params = "find=ByCorreoELike", method = RequestMethod.GET)
    public String findArbitroesByCorreoELike(@RequestParam("correoE") String correoE, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("arbitroes", Arbitro.findArbitroesByCorreoELike(correoE, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Arbitro.countFindArbitroesByCorreoELike(correoE) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("arbitroes", Arbitro.findArbitroesByCorreoELike(correoE, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "arbitroes/list";
    }

	@RequestMapping(params = { "find=ByCorreoELikeAndStatus", "form" }, method = RequestMethod.GET)
    public String findArbitroesByCorreoELikeAndStatusForm(Model uiModel) {
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        return "arbitroes/findArbitroesByCorreoELikeAndStatus";
    }

	@RequestMapping(params = "find=ByCorreoELikeAndStatus", method = RequestMethod.GET)
    public String findArbitroesByCorreoELikeAndStatus(@RequestParam("correoE") String correoE, @RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("arbitroes", Arbitro.findArbitroesByCorreoELikeAndStatus(correoE, status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Arbitro.countFindArbitroesByCorreoELikeAndStatus(correoE, status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("arbitroes", Arbitro.findArbitroesByCorreoELikeAndStatus(correoE, status, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "arbitroes/list";
    }

	@RequestMapping(params = { "find=ByNombreLike", "form" }, method = RequestMethod.GET)
    public String findArbitroesByNombreLikeForm(Model uiModel) {
        return "arbitroes/findArbitroesByNombreLike";
    }

	@RequestMapping(params = "find=ByNombreLike", method = RequestMethod.GET)
    public String findArbitroesByNombreLike(@RequestParam("nombre") String nombre, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("arbitroes", Arbitro.findArbitroesByNombreLike(nombre, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Arbitro.countFindArbitroesByNombreLike(nombre) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("arbitroes", Arbitro.findArbitroesByNombreLike(nombre, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "arbitroes/list";
    }

	@RequestMapping(params = { "find=ByNombreLikeAndApellidoPaternoLike", "form" }, method = RequestMethod.GET)
    public String findArbitroesByNombreLikeAndApellidoPaternoLikeForm(Model uiModel) {
        return "arbitroes/findArbitroesByNombreLikeAndApellidoPaternoLike";
    }

	@RequestMapping(params = "find=ByNombreLikeAndApellidoPaternoLike", method = RequestMethod.GET)
    public String findArbitroesByNombreLikeAndApellidoPaternoLike(@RequestParam("nombre") String nombre, @RequestParam("apellidoPaterno") String apellidoPaterno, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("arbitroes", Arbitro.findArbitroesByNombreLikeAndApellidoPaternoLike(nombre, apellidoPaterno, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Arbitro.countFindArbitroesByNombreLikeAndApellidoPaternoLike(nombre, apellidoPaterno) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("arbitroes", Arbitro.findArbitroesByNombreLikeAndApellidoPaternoLike(nombre, apellidoPaterno, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "arbitroes/list";
    }

	@RequestMapping(params = { "find=ByNombreLikeAndApellidoPaternoLikeAndStatus", "form" }, method = RequestMethod.GET)
    public String findArbitroesByNombreLikeAndApellidoPaternoLikeAndStatusForm(Model uiModel) {
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        return "arbitroes/findArbitroesByNombreLikeAndApellidoPaternoLikeAndStatus";
    }

	@RequestMapping(params = "find=ByNombreLikeAndApellidoPaternoLikeAndStatus", method = RequestMethod.GET)
    public String findArbitroesByNombreLikeAndApellidoPaternoLikeAndStatus(@RequestParam("nombre") String nombre, @RequestParam("apellidoPaterno") String apellidoPaterno, @RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("arbitroes", Arbitro.findArbitroesByNombreLikeAndApellidoPaternoLikeAndStatus(nombre, apellidoPaterno, status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Arbitro.countFindArbitroesByNombreLikeAndApellidoPaternoLikeAndStatus(nombre, apellidoPaterno, status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("arbitroes", Arbitro.findArbitroesByNombreLikeAndApellidoPaternoLikeAndStatus(nombre, apellidoPaterno, status, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "arbitroes/list";
    }

	@RequestMapping(params = { "find=ByNombreLikeAndStatus", "form" }, method = RequestMethod.GET)
    public String findArbitroesByNombreLikeAndStatusForm(Model uiModel) {
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        return "arbitroes/findArbitroesByNombreLikeAndStatus";
    }

	@RequestMapping(params = "find=ByNombreLikeAndStatus", method = RequestMethod.GET)
    public String findArbitroesByNombreLikeAndStatus(@RequestParam("nombre") String nombre, @RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("arbitroes", Arbitro.findArbitroesByNombreLikeAndStatus(nombre, status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Arbitro.countFindArbitroesByNombreLikeAndStatus(nombre, status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("arbitroes", Arbitro.findArbitroesByNombreLikeAndStatus(nombre, status, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "arbitroes/list";
    }

	@RequestMapping(params = { "find=ByStatus", "form" }, method = RequestMethod.GET)
    public String findArbitroesByStatusForm(Model uiModel) {
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        return "arbitroes/findArbitroesByStatus";
    }

	@RequestMapping(params = "find=ByStatus", method = RequestMethod.GET)
    public String findArbitroesByStatus(@RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("arbitroes", Arbitro.findArbitroesByStatus(status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Arbitro.countFindArbitroesByStatus(status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("arbitroes", Arbitro.findArbitroesByStatus(status, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "arbitroes/list";
    }

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Arbitro arbitro, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, arbitro);
            return "arbitroes/create";
        }
        uiModel.asMap().clear();
        arbitro.persist();
        return "redirect:/arbitroes/" + encodeUrlPathSegment(arbitro.getId().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Arbitro());
        return "arbitroes/create";
    }

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("arbitro", Arbitro.findArbitro(id));
        uiModel.addAttribute("itemId", id);
        return "arbitroes/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("arbitroes", Arbitro.findArbitroEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Arbitro.countArbitroes() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("arbitroes", Arbitro.findAllArbitroes(sortFieldName, sortOrder));
        }
        addDateTimeFormatPatterns(uiModel);
        return "arbitroes/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Arbitro arbitro, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, arbitro);
            return "arbitroes/update";
        }
        uiModel.asMap().clear();
        arbitro.merge();
        return "redirect:/arbitroes/" + encodeUrlPathSegment(arbitro.getId().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, Arbitro.findArbitro(id));
        return "arbitroes/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Arbitro arbitro = Arbitro.findArbitro(id);
        arbitro.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/arbitroes";
    }

	void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("arbitro_fechacreacion_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("arbitro_fechamodificacion_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }

	void populateEditForm(Model uiModel, Arbitro arbitro) {
        uiModel.addAttribute("arbitro", arbitro);
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

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> showJson(@PathVariable("id") Long id) {
        Arbitro arbitro = Arbitro.findArbitro(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        if (arbitro == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(arbitro.toJson(), headers, HttpStatus.OK);
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<Arbitro> result = Arbitro.findAllArbitroes();
        return new ResponseEntity<String>(Arbitro.toJsonArray(result), headers, HttpStatus.OK);
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody String json, UriComponentsBuilder uriBuilder) {
        Arbitro arbitro = Arbitro.fromJsonToArbitro(json);
        arbitro.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        RequestMapping a = (RequestMapping) getClass().getAnnotation(RequestMapping.class);
        headers.add("Location",uriBuilder.path(a.value()[0]+"/"+arbitro.getId().toString()).build().toUriString());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/jsonArray", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJsonArray(@RequestBody String json) {
        for (Arbitro arbitro: Arbitro.fromJsonArrayToArbitroes(json)) {
            arbitro.persist();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody String json, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        Arbitro arbitro = Arbitro.fromJsonToArbitro(json);
        arbitro.setId(id);
        if (arbitro.merge() == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        Arbitro arbitro = Arbitro.findArbitro(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (arbitro == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        arbitro.remove();
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByCorreoEEquals", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindArbitroesByCorreoEEquals(@RequestParam("correoE") String correoE) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Arbitro.toJsonArray(Arbitro.findArbitroesByCorreoEEquals(correoE).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByCorreoEEqualsAndStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindArbitroesByCorreoEEqualsAndStatus(@RequestParam("correoE") String correoE, @RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Arbitro.toJsonArray(Arbitro.findArbitroesByCorreoEEqualsAndStatus(correoE, status).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByCorreoELike", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindArbitroesByCorreoELike(@RequestParam("correoE") String correoE) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Arbitro.toJsonArray(Arbitro.findArbitroesByCorreoELike(correoE).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByCorreoELikeAndStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindArbitroesByCorreoELikeAndStatus(@RequestParam("correoE") String correoE, @RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Arbitro.toJsonArray(Arbitro.findArbitroesByCorreoELikeAndStatus(correoE, status).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByNombreLike", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindArbitroesByNombreLike(@RequestParam("nombre") String nombre) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Arbitro.toJsonArray(Arbitro.findArbitroesByNombreLike(nombre).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByNombreLikeAndApellidoPaternoLike", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindArbitroesByNombreLikeAndApellidoPaternoLike(@RequestParam("nombre") String nombre, @RequestParam("apellidoPaterno") String apellidoPaterno) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Arbitro.toJsonArray(Arbitro.findArbitroesByNombreLikeAndApellidoPaternoLike(nombre, apellidoPaterno).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByNombreLikeAndApellidoPaternoLikeAndStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindArbitroesByNombreLikeAndApellidoPaternoLikeAndStatus(@RequestParam("nombre") String nombre, @RequestParam("apellidoPaterno") String apellidoPaterno, @RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Arbitro.toJsonArray(Arbitro.findArbitroesByNombreLikeAndApellidoPaternoLikeAndStatus(nombre, apellidoPaterno, status).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByNombreLikeAndStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindArbitroesByNombreLikeAndStatus(@RequestParam("nombre") String nombre, @RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Arbitro.toJsonArray(Arbitro.findArbitroesByNombreLikeAndStatus(nombre, status).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindArbitroesByStatus(@RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Arbitro.toJsonArray(Arbitro.findArbitroesByStatus(status).getResultList()), headers, HttpStatus.OK);
    }
}
