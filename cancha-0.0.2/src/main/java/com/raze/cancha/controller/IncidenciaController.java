package com.raze.cancha.controller;
import com.raze.cancha.domain.Incidencia;
import com.raze.cancha.domain.Sucursal;
import com.raze.cancha.domain.Usuario;
import com.raze.cancha.reference.Status;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.format.annotation.DateTimeFormat;
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

@RooWebJson(jsonObject = Incidencia.class)
@Controller
@RequestMapping("/incidencias")
@RooWebScaffold(path = "incidencias", formBackingObject = Incidencia.class)
@RooWebFinder
public class IncidenciaController {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> showJson(@PathVariable("id") Long id) {
        Incidencia incidencia = Incidencia.findIncidencia(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        if (incidencia == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(incidencia.toJson(), headers, HttpStatus.OK);
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<Incidencia> result = Incidencia.findAllIncidencias();
        return new ResponseEntity<String>(Incidencia.toJsonArray(result), headers, HttpStatus.OK);
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody String json, UriComponentsBuilder uriBuilder) {
        Incidencia incidencia = Incidencia.fromJsonToIncidencia(json);
        incidencia.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        RequestMapping a = (RequestMapping) getClass().getAnnotation(RequestMapping.class);
        headers.add("Location",uriBuilder.path(a.value()[0]+"/"+incidencia.getId().toString()).build().toUriString());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/jsonArray", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJsonArray(@RequestBody String json) {
        for (Incidencia incidencia: Incidencia.fromJsonArrayToIncidencias(json)) {
            incidencia.persist();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody String json, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        Incidencia incidencia = Incidencia.fromJsonToIncidencia(json);
        incidencia.setId(id);
        if (incidencia.merge() == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        Incidencia incidencia = Incidencia.findIncidencia(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (incidencia == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        incidencia.remove();
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByFechaCreacionBetween", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindIncidenciasByFechaCreacionBetween(@RequestParam("minFechaCreacion") @DateTimeFormat(style = "M-") Date minFechaCreacion, @RequestParam("maxFechaCreacion") @DateTimeFormat(style = "M-") Date maxFechaCreacion) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Incidencia.toJsonArray(Incidencia.findIncidenciasByFechaCreacionBetween(minFechaCreacion, maxFechaCreacion).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByFechaCreacionBetweenAndStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindIncidenciasByFechaCreacionBetweenAndStatus(@RequestParam("minFechaCreacion") @DateTimeFormat(style = "M-") Date minFechaCreacion, @RequestParam("maxFechaCreacion") @DateTimeFormat(style = "M-") Date maxFechaCreacion, @RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Incidencia.toJsonArray(Incidencia.findIncidenciasByFechaCreacionBetweenAndStatus(minFechaCreacion, maxFechaCreacion, status).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByFechaCreacionEquals", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindIncidenciasByFechaCreacionEquals(@RequestParam("fechaCreacion") @DateTimeFormat(style = "M-") Date fechaCreacion) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Incidencia.toJsonArray(Incidencia.findIncidenciasByFechaCreacionEquals(fechaCreacion).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByFechaCreacionEqualsAndStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindIncidenciasByFechaCreacionEqualsAndStatus(@RequestParam("fechaCreacion") @DateTimeFormat(style = "M-") Date fechaCreacion, @RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Incidencia.toJsonArray(Incidencia.findIncidenciasByFechaCreacionEqualsAndStatus(fechaCreacion, status).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByIdSucursal", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindIncidenciasByIdSucursal(@RequestParam("idSucursal") Sucursal idSucursal) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Incidencia.toJsonArray(Incidencia.findIncidenciasByIdSucursal(idSucursal).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByIdSucursalAndStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindIncidenciasByIdSucursalAndStatus(@RequestParam("idSucursal") Sucursal idSucursal, @RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Incidencia.toJsonArray(Incidencia.findIncidenciasByIdSucursalAndStatus(idSucursal, status).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByIdUsuario", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindIncidenciasByIdUsuario(@RequestParam("idUsuario") Usuario idUsuario) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Incidencia.toJsonArray(Incidencia.findIncidenciasByIdUsuario(idUsuario).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByIdUsuarioAndStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindIncidenciasByIdUsuarioAndStatus(@RequestParam("idUsuario") Usuario idUsuario, @RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Incidencia.toJsonArray(Incidencia.findIncidenciasByIdUsuarioAndStatus(idUsuario, status).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindIncidenciasByStatus(@RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Incidencia.toJsonArray(Incidencia.findIncidenciasByStatus(status).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = { "find=ByFechaCreacionBetween", "form" }, method = RequestMethod.GET)
    public String findIncidenciasByFechaCreacionBetweenForm(Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        return "incidencias/findIncidenciasByFechaCreacionBetween";
    }

	@RequestMapping(params = "find=ByFechaCreacionBetween", method = RequestMethod.GET)
    public String findIncidenciasByFechaCreacionBetween(@RequestParam("minFechaCreacion") @DateTimeFormat(style = "M-") Date minFechaCreacion, @RequestParam("maxFechaCreacion") @DateTimeFormat(style = "M-") Date maxFechaCreacion, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("incidencias", Incidencia.findIncidenciasByFechaCreacionBetween(minFechaCreacion, maxFechaCreacion, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Incidencia.countFindIncidenciasByFechaCreacionBetween(minFechaCreacion, maxFechaCreacion) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("incidencias", Incidencia.findIncidenciasByFechaCreacionBetween(minFechaCreacion, maxFechaCreacion, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "incidencias/list";
    }

	@RequestMapping(params = { "find=ByFechaCreacionBetweenAndStatus", "form" }, method = RequestMethod.GET)
    public String findIncidenciasByFechaCreacionBetweenAndStatusForm(Model uiModel) {
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        addDateTimeFormatPatterns(uiModel);
        return "incidencias/findIncidenciasByFechaCreacionBetweenAndStatus";
    }

	@RequestMapping(params = "find=ByFechaCreacionBetweenAndStatus", method = RequestMethod.GET)
    public String findIncidenciasByFechaCreacionBetweenAndStatus(@RequestParam("minFechaCreacion") @DateTimeFormat(style = "M-") Date minFechaCreacion, @RequestParam("maxFechaCreacion") @DateTimeFormat(style = "M-") Date maxFechaCreacion, @RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("incidencias", Incidencia.findIncidenciasByFechaCreacionBetweenAndStatus(minFechaCreacion, maxFechaCreacion, status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Incidencia.countFindIncidenciasByFechaCreacionBetweenAndStatus(minFechaCreacion, maxFechaCreacion, status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("incidencias", Incidencia.findIncidenciasByFechaCreacionBetweenAndStatus(minFechaCreacion, maxFechaCreacion, status, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "incidencias/list";
    }

	@RequestMapping(params = { "find=ByFechaCreacionEquals", "form" }, method = RequestMethod.GET)
    public String findIncidenciasByFechaCreacionEqualsForm(Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        return "incidencias/findIncidenciasByFechaCreacionEquals";
    }

	@RequestMapping(params = "find=ByFechaCreacionEquals", method = RequestMethod.GET)
    public String findIncidenciasByFechaCreacionEquals(@RequestParam("fechaCreacion") @DateTimeFormat(style = "M-") Date fechaCreacion, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("incidencias", Incidencia.findIncidenciasByFechaCreacionEquals(fechaCreacion, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Incidencia.countFindIncidenciasByFechaCreacionEquals(fechaCreacion) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("incidencias", Incidencia.findIncidenciasByFechaCreacionEquals(fechaCreacion, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "incidencias/list";
    }

	@RequestMapping(params = { "find=ByFechaCreacionEqualsAndStatus", "form" }, method = RequestMethod.GET)
    public String findIncidenciasByFechaCreacionEqualsAndStatusForm(Model uiModel) {
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        addDateTimeFormatPatterns(uiModel);
        return "incidencias/findIncidenciasByFechaCreacionEqualsAndStatus";
    }

	@RequestMapping(params = "find=ByFechaCreacionEqualsAndStatus", method = RequestMethod.GET)
    public String findIncidenciasByFechaCreacionEqualsAndStatus(@RequestParam("fechaCreacion") @DateTimeFormat(style = "M-") Date fechaCreacion, @RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("incidencias", Incidencia.findIncidenciasByFechaCreacionEqualsAndStatus(fechaCreacion, status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Incidencia.countFindIncidenciasByFechaCreacionEqualsAndStatus(fechaCreacion, status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("incidencias", Incidencia.findIncidenciasByFechaCreacionEqualsAndStatus(fechaCreacion, status, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "incidencias/list";
    }

	@RequestMapping(params = { "find=ByIdSucursal", "form" }, method = RequestMethod.GET)
    public String findIncidenciasByIdSucursalForm(Model uiModel) {
        uiModel.addAttribute("sucursals", Sucursal.findAllSucursals());
        return "incidencias/findIncidenciasByIdSucursal";
    }

	@RequestMapping(params = "find=ByIdSucursal", method = RequestMethod.GET)
    public String findIncidenciasByIdSucursal(@RequestParam("idSucursal") Sucursal idSucursal, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("incidencias", Incidencia.findIncidenciasByIdSucursal(idSucursal, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Incidencia.countFindIncidenciasByIdSucursal(idSucursal) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("incidencias", Incidencia.findIncidenciasByIdSucursal(idSucursal, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "incidencias/list";
    }

	@RequestMapping(params = { "find=ByIdSucursalAndStatus", "form" }, method = RequestMethod.GET)
    public String findIncidenciasByIdSucursalAndStatusForm(Model uiModel) {
        uiModel.addAttribute("sucursals", Sucursal.findAllSucursals());
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        return "incidencias/findIncidenciasByIdSucursalAndStatus";
    }

	@RequestMapping(params = "find=ByIdSucursalAndStatus", method = RequestMethod.GET)
    public String findIncidenciasByIdSucursalAndStatus(@RequestParam("idSucursal") Sucursal idSucursal, @RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("incidencias", Incidencia.findIncidenciasByIdSucursalAndStatus(idSucursal, status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Incidencia.countFindIncidenciasByIdSucursalAndStatus(idSucursal, status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("incidencias", Incidencia.findIncidenciasByIdSucursalAndStatus(idSucursal, status, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "incidencias/list";
    }

	@RequestMapping(params = { "find=ByIdUsuario", "form" }, method = RequestMethod.GET)
    public String findIncidenciasByIdUsuarioForm(Model uiModel) {
        uiModel.addAttribute("usuarios", Usuario.findAllUsuarios());
        return "incidencias/findIncidenciasByIdUsuario";
    }

	@RequestMapping(params = "find=ByIdUsuario", method = RequestMethod.GET)
    public String findIncidenciasByIdUsuario(@RequestParam("idUsuario") Usuario idUsuario, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("incidencias", Incidencia.findIncidenciasByIdUsuario(idUsuario, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Incidencia.countFindIncidenciasByIdUsuario(idUsuario) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("incidencias", Incidencia.findIncidenciasByIdUsuario(idUsuario, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "incidencias/list";
    }

	@RequestMapping(params = { "find=ByIdUsuarioAndStatus", "form" }, method = RequestMethod.GET)
    public String findIncidenciasByIdUsuarioAndStatusForm(Model uiModel) {
        uiModel.addAttribute("usuarios", Usuario.findAllUsuarios());
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        return "incidencias/findIncidenciasByIdUsuarioAndStatus";
    }

	@RequestMapping(params = "find=ByIdUsuarioAndStatus", method = RequestMethod.GET)
    public String findIncidenciasByIdUsuarioAndStatus(@RequestParam("idUsuario") Usuario idUsuario, @RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("incidencias", Incidencia.findIncidenciasByIdUsuarioAndStatus(idUsuario, status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Incidencia.countFindIncidenciasByIdUsuarioAndStatus(idUsuario, status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("incidencias", Incidencia.findIncidenciasByIdUsuarioAndStatus(idUsuario, status, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "incidencias/list";
    }

	@RequestMapping(params = { "find=ByStatus", "form" }, method = RequestMethod.GET)
    public String findIncidenciasByStatusForm(Model uiModel) {
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        return "incidencias/findIncidenciasByStatus";
    }

	@RequestMapping(params = "find=ByStatus", method = RequestMethod.GET)
    public String findIncidenciasByStatus(@RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("incidencias", Incidencia.findIncidenciasByStatus(status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Incidencia.countFindIncidenciasByStatus(status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("incidencias", Incidencia.findIncidenciasByStatus(status, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "incidencias/list";
    }

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Incidencia incidencia, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, incidencia);
            return "incidencias/create";
        }
        uiModel.asMap().clear();
        incidencia.persist();
        return "redirect:/incidencias/" + encodeUrlPathSegment(incidencia.getId().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Incidencia());
        return "incidencias/create";
    }

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("incidencia", Incidencia.findIncidencia(id));
        uiModel.addAttribute("itemId", id);
        return "incidencias/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("incidencias", Incidencia.findIncidenciaEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Incidencia.countIncidencias() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("incidencias", Incidencia.findAllIncidencias(sortFieldName, sortOrder));
        }
        addDateTimeFormatPatterns(uiModel);
        return "incidencias/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Incidencia incidencia, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, incidencia);
            return "incidencias/update";
        }
        uiModel.asMap().clear();
        incidencia.merge();
        return "redirect:/incidencias/" + encodeUrlPathSegment(incidencia.getId().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, Incidencia.findIncidencia(id));
        return "incidencias/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Incidencia incidencia = Incidencia.findIncidencia(id);
        incidencia.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/incidencias";
    }

	void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("incidencia_fechacreacion_date_format", org.joda.time.format.DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("incidencia_fechamodificacion_date_format", org.joda.time.format.DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }

	void populateEditForm(Model uiModel, Incidencia incidencia) {
        uiModel.addAttribute("incidencia", incidencia);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("sucursals", Sucursal.findAllSucursals());
        uiModel.addAttribute("usuarios", Usuario.findAllUsuarios());
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
