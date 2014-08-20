package com.raze.cancha.controller;
import com.raze.cancha.domain.Delegado;
import com.raze.cancha.domain.Equipo;
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

@RooWebJson(jsonObject = Delegado.class)
@Controller
@RequestMapping("/delegadoes")
@RooWebScaffold(path = "delegadoes", formBackingObject = Delegado.class)
@RooWebFinder
public class DelegadoController {

	@RequestMapping(params = { "find=ByCorreoELike", "form" }, method = RequestMethod.GET)
    public String findDelegadoesByCorreoELikeForm(Model uiModel) {
        return "delegadoes/findDelegadoesByCorreoELike";
    }

	@RequestMapping(params = "find=ByCorreoELike", method = RequestMethod.GET)
    public String findDelegadoesByCorreoELike(@RequestParam("correoE") String correoE, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("delegadoes", Delegado.findDelegadoesByCorreoELike(correoE, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Delegado.countFindDelegadoesByCorreoELike(correoE) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("delegadoes", Delegado.findDelegadoesByCorreoELike(correoE, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "delegadoes/list";
    }

	@RequestMapping(params = { "find=ByCorreoELikeAndStatus", "form" }, method = RequestMethod.GET)
    public String findDelegadoesByCorreoELikeAndStatusForm(Model uiModel) {
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        return "delegadoes/findDelegadoesByCorreoELikeAndStatus";
    }

	@RequestMapping(params = "find=ByCorreoELikeAndStatus", method = RequestMethod.GET)
    public String findDelegadoesByCorreoELikeAndStatus(@RequestParam("correoE") String correoE, @RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("delegadoes", Delegado.findDelegadoesByCorreoELikeAndStatus(correoE, status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Delegado.countFindDelegadoesByCorreoELikeAndStatus(correoE, status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("delegadoes", Delegado.findDelegadoesByCorreoELikeAndStatus(correoE, status, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "delegadoes/list";
    }

	@RequestMapping(params = { "find=ByIdEquipo", "form" }, method = RequestMethod.GET)
    public String findDelegadoesByIdEquipoForm(Model uiModel) {
        uiModel.addAttribute("equipoes", Equipo.findAllEquipoes());
        return "delegadoes/findDelegadoesByIdEquipo";
    }

	@RequestMapping(params = "find=ByIdEquipo", method = RequestMethod.GET)
    public String findDelegadoesByIdEquipo(@RequestParam("idEquipo") Equipo idEquipo, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("delegadoes", Delegado.findDelegadoesByIdEquipo(idEquipo, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Delegado.countFindDelegadoesByIdEquipo(idEquipo) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("delegadoes", Delegado.findDelegadoesByIdEquipo(idEquipo, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "delegadoes/list";
    }

	@RequestMapping(params = { "find=ByIdEquipoAndStatus", "form" }, method = RequestMethod.GET)
    public String findDelegadoesByIdEquipoAndStatusForm(Model uiModel) {
        uiModel.addAttribute("equipoes", Equipo.findAllEquipoes());
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        return "delegadoes/findDelegadoesByIdEquipoAndStatus";
    }

	@RequestMapping(params = "find=ByIdEquipoAndStatus", method = RequestMethod.GET)
    public String findDelegadoesByIdEquipoAndStatus(@RequestParam("idEquipo") Equipo idEquipo, @RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("delegadoes", Delegado.findDelegadoesByIdEquipoAndStatus(idEquipo, status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Delegado.countFindDelegadoesByIdEquipoAndStatus(idEquipo, status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("delegadoes", Delegado.findDelegadoesByIdEquipoAndStatus(idEquipo, status, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "delegadoes/list";
    }

	@RequestMapping(params = { "find=ByNombreLikeAndApellidoPaternoLike", "form" }, method = RequestMethod.GET)
    public String findDelegadoesByNombreLikeAndApellidoPaternoLikeForm(Model uiModel) {
        return "delegadoes/findDelegadoesByNombreLikeAndApellidoPaternoLike";
    }

	@RequestMapping(params = "find=ByNombreLikeAndApellidoPaternoLike", method = RequestMethod.GET)
    public String findDelegadoesByNombreLikeAndApellidoPaternoLike(@RequestParam("nombre") String nombre, @RequestParam("apellidoPaterno") String apellidoPaterno, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("delegadoes", Delegado.findDelegadoesByNombreLikeAndApellidoPaternoLike(nombre, apellidoPaterno, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Delegado.countFindDelegadoesByNombreLikeAndApellidoPaternoLike(nombre, apellidoPaterno) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("delegadoes", Delegado.findDelegadoesByNombreLikeAndApellidoPaternoLike(nombre, apellidoPaterno, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "delegadoes/list";
    }

	@RequestMapping(params = { "find=ByNombreLikeAndApellidoPaternoLikeAndStatus", "form" }, method = RequestMethod.GET)
    public String findDelegadoesByNombreLikeAndApellidoPaternoLikeAndStatusForm(Model uiModel) {
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        return "delegadoes/findDelegadoesByNombreLikeAndApellidoPaternoLikeAndStatus";
    }

	@RequestMapping(params = "find=ByNombreLikeAndApellidoPaternoLikeAndStatus", method = RequestMethod.GET)
    public String findDelegadoesByNombreLikeAndApellidoPaternoLikeAndStatus(@RequestParam("nombre") String nombre, @RequestParam("apellidoPaterno") String apellidoPaterno, @RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("delegadoes", Delegado.findDelegadoesByNombreLikeAndApellidoPaternoLikeAndStatus(nombre, apellidoPaterno, status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Delegado.countFindDelegadoesByNombreLikeAndApellidoPaternoLikeAndStatus(nombre, apellidoPaterno, status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("delegadoes", Delegado.findDelegadoesByNombreLikeAndApellidoPaternoLikeAndStatus(nombre, apellidoPaterno, status, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "delegadoes/list";
    }

	@RequestMapping(params = { "find=ByStatus", "form" }, method = RequestMethod.GET)
    public String findDelegadoesByStatusForm(Model uiModel) {
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        return "delegadoes/findDelegadoesByStatus";
    }

	@RequestMapping(params = "find=ByStatus", method = RequestMethod.GET)
    public String findDelegadoesByStatus(@RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("delegadoes", Delegado.findDelegadoesByStatus(status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Delegado.countFindDelegadoesByStatus(status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("delegadoes", Delegado.findDelegadoesByStatus(status, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "delegadoes/list";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> showJson(@PathVariable("id") Long id) {
        Delegado delegado = Delegado.findDelegado(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        if (delegado == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(delegado.toJson(), headers, HttpStatus.OK);
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<Delegado> result = Delegado.findAllDelegadoes();
        return new ResponseEntity<String>(Delegado.toJsonArray(result), headers, HttpStatus.OK);
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody String json, UriComponentsBuilder uriBuilder) {
        Delegado delegado = Delegado.fromJsonToDelegado(json);
        delegado.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        RequestMapping a = (RequestMapping) getClass().getAnnotation(RequestMapping.class);
        headers.add("Location",uriBuilder.path(a.value()[0]+"/"+delegado.getId().toString()).build().toUriString());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/jsonArray", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJsonArray(@RequestBody String json) {
        for (Delegado delegado: Delegado.fromJsonArrayToDelegadoes(json)) {
            delegado.persist();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody String json, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        Delegado delegado = Delegado.fromJsonToDelegado(json);
        delegado.setId(id);
        if (delegado.merge() == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        Delegado delegado = Delegado.findDelegado(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (delegado == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        delegado.remove();
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByCorreoELike", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindDelegadoesByCorreoELike(@RequestParam("correoE") String correoE) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Delegado.toJsonArray(Delegado.findDelegadoesByCorreoELike(correoE).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByCorreoELikeAndStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindDelegadoesByCorreoELikeAndStatus(@RequestParam("correoE") String correoE, @RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Delegado.toJsonArray(Delegado.findDelegadoesByCorreoELikeAndStatus(correoE, status).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByIdEquipo", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindDelegadoesByIdEquipo(@RequestParam("idEquipo") Equipo idEquipo) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Delegado.toJsonArray(Delegado.findDelegadoesByIdEquipo(idEquipo).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByIdEquipoAndStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindDelegadoesByIdEquipoAndStatus(@RequestParam("idEquipo") Equipo idEquipo, @RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Delegado.toJsonArray(Delegado.findDelegadoesByIdEquipoAndStatus(idEquipo, status).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByNombreLikeAndApellidoPaternoLike", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindDelegadoesByNombreLikeAndApellidoPaternoLike(@RequestParam("nombre") String nombre, @RequestParam("apellidoPaterno") String apellidoPaterno) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Delegado.toJsonArray(Delegado.findDelegadoesByNombreLikeAndApellidoPaternoLike(nombre, apellidoPaterno).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByNombreLikeAndApellidoPaternoLikeAndStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindDelegadoesByNombreLikeAndApellidoPaternoLikeAndStatus(@RequestParam("nombre") String nombre, @RequestParam("apellidoPaterno") String apellidoPaterno, @RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Delegado.toJsonArray(Delegado.findDelegadoesByNombreLikeAndApellidoPaternoLikeAndStatus(nombre, apellidoPaterno, status).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindDelegadoesByStatus(@RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Delegado.toJsonArray(Delegado.findDelegadoesByStatus(status).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Delegado delegado, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, delegado);
            return "delegadoes/create";
        }
        uiModel.asMap().clear();
        delegado.persist();
        return "redirect:/delegadoes/" + encodeUrlPathSegment(delegado.getId().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Delegado());
        return "delegadoes/create";
    }

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("delegado", Delegado.findDelegado(id));
        uiModel.addAttribute("itemId", id);
        return "delegadoes/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("delegadoes", Delegado.findDelegadoEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Delegado.countDelegadoes() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("delegadoes", Delegado.findAllDelegadoes(sortFieldName, sortOrder));
        }
        addDateTimeFormatPatterns(uiModel);
        return "delegadoes/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Delegado delegado, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, delegado);
            return "delegadoes/update";
        }
        uiModel.asMap().clear();
        delegado.merge();
        return "redirect:/delegadoes/" + encodeUrlPathSegment(delegado.getId().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, Delegado.findDelegado(id));
        return "delegadoes/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Delegado delegado = Delegado.findDelegado(id);
        delegado.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/delegadoes";
    }

	void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("delegado_fechacreacion_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("delegado_fechamodificacion_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }

	void populateEditForm(Model uiModel, Delegado delegado) {
        uiModel.addAttribute("delegado", delegado);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("equipoes", Equipo.findAllEquipoes());
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
