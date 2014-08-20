package com.raze.cancha.controller;
import com.raze.cancha.domain.Equipo;
import com.raze.cancha.domain.Jugador;
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

@RooWebJson(jsonObject = Jugador.class)
@Controller
@RequestMapping("/jugadors")
@RooWebScaffold(path = "jugadors", formBackingObject = Jugador.class)
@RooWebFinder
public class JugadorController {

	@RequestMapping(params = { "find=ByCorreoELike", "form" }, method = RequestMethod.GET)
    public String findJugadorsByCorreoELikeForm(Model uiModel) {
        return "jugadors/findJugadorsByCorreoELike";
    }

	@RequestMapping(params = "find=ByCorreoELike", method = RequestMethod.GET)
    public String findJugadorsByCorreoELike(@RequestParam("correoE") String correoE, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("jugadors", Jugador.findJugadorsByCorreoELike(correoE, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Jugador.countFindJugadorsByCorreoELike(correoE) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("jugadors", Jugador.findJugadorsByCorreoELike(correoE, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "jugadors/list";
    }

	@RequestMapping(params = { "find=ByCorreoELikeAndStatus", "form" }, method = RequestMethod.GET)
    public String findJugadorsByCorreoELikeAndStatusForm(Model uiModel) {
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        return "jugadors/findJugadorsByCorreoELikeAndStatus";
    }

	@RequestMapping(params = "find=ByCorreoELikeAndStatus", method = RequestMethod.GET)
    public String findJugadorsByCorreoELikeAndStatus(@RequestParam("correoE") String correoE, @RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("jugadors", Jugador.findJugadorsByCorreoELikeAndStatus(correoE, status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Jugador.countFindJugadorsByCorreoELikeAndStatus(correoE, status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("jugadors", Jugador.findJugadorsByCorreoELikeAndStatus(correoE, status, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "jugadors/list";
    }

	@RequestMapping(params = { "find=ByIdEquipo", "form" }, method = RequestMethod.GET)
    public String findJugadorsByIdEquipoForm(Model uiModel) {
        uiModel.addAttribute("equipoes", Equipo.findAllEquipoes());
        return "jugadors/findJugadorsByIdEquipo";
    }

	@RequestMapping(params = "find=ByIdEquipo", method = RequestMethod.GET)
    public String findJugadorsByIdEquipo(@RequestParam("idEquipo") Equipo idEquipo, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("jugadors", Jugador.findJugadorsByIdEquipo(idEquipo, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Jugador.countFindJugadorsByIdEquipo(idEquipo) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("jugadors", Jugador.findJugadorsByIdEquipo(idEquipo, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "jugadors/list";
    }

	@RequestMapping(params = { "find=ByIdEquipoAndStatus", "form" }, method = RequestMethod.GET)
    public String findJugadorsByIdEquipoAndStatusForm(Model uiModel) {
        uiModel.addAttribute("equipoes", Equipo.findAllEquipoes());
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        return "jugadors/findJugadorsByIdEquipoAndStatus";
    }

	@RequestMapping(params = "find=ByIdEquipoAndStatus", method = RequestMethod.GET)
    public String findJugadorsByIdEquipoAndStatus(@RequestParam("idEquipo") Equipo idEquipo, @RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("jugadors", Jugador.findJugadorsByIdEquipoAndStatus(idEquipo, status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Jugador.countFindJugadorsByIdEquipoAndStatus(idEquipo, status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("jugadors", Jugador.findJugadorsByIdEquipoAndStatus(idEquipo, status, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "jugadors/list";
    }

	@RequestMapping(params = { "find=ByNombreLikeAndApellidoPaternoLike", "form" }, method = RequestMethod.GET)
    public String findJugadorsByNombreLikeAndApellidoPaternoLikeForm(Model uiModel) {
        return "jugadors/findJugadorsByNombreLikeAndApellidoPaternoLike";
    }

	@RequestMapping(params = "find=ByNombreLikeAndApellidoPaternoLike", method = RequestMethod.GET)
    public String findJugadorsByNombreLikeAndApellidoPaternoLike(@RequestParam("nombre") String nombre, @RequestParam("apellidoPaterno") String apellidoPaterno, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("jugadors", Jugador.findJugadorsByNombreLikeAndApellidoPaternoLike(nombre, apellidoPaterno, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Jugador.countFindJugadorsByNombreLikeAndApellidoPaternoLike(nombre, apellidoPaterno) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("jugadors", Jugador.findJugadorsByNombreLikeAndApellidoPaternoLike(nombre, apellidoPaterno, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "jugadors/list";
    }

	@RequestMapping(params = { "find=ByNombreLikeAndApellidoPaternoLikeAndStatus", "form" }, method = RequestMethod.GET)
    public String findJugadorsByNombreLikeAndApellidoPaternoLikeAndStatusForm(Model uiModel) {
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        return "jugadors/findJugadorsByNombreLikeAndApellidoPaternoLikeAndStatus";
    }

	@RequestMapping(params = "find=ByNombreLikeAndApellidoPaternoLikeAndStatus", method = RequestMethod.GET)
    public String findJugadorsByNombreLikeAndApellidoPaternoLikeAndStatus(@RequestParam("nombre") String nombre, @RequestParam("apellidoPaterno") String apellidoPaterno, @RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("jugadors", Jugador.findJugadorsByNombreLikeAndApellidoPaternoLikeAndStatus(nombre, apellidoPaterno, status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Jugador.countFindJugadorsByNombreLikeAndApellidoPaternoLikeAndStatus(nombre, apellidoPaterno, status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("jugadors", Jugador.findJugadorsByNombreLikeAndApellidoPaternoLikeAndStatus(nombre, apellidoPaterno, status, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "jugadors/list";
    }

	@RequestMapping(params = { "find=ByStatus", "form" }, method = RequestMethod.GET)
    public String findJugadorsByStatusForm(Model uiModel) {
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        return "jugadors/findJugadorsByStatus";
    }

	@RequestMapping(params = "find=ByStatus", method = RequestMethod.GET)
    public String findJugadorsByStatus(@RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("jugadors", Jugador.findJugadorsByStatus(status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Jugador.countFindJugadorsByStatus(status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("jugadors", Jugador.findJugadorsByStatus(status, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "jugadors/list";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> showJson(@PathVariable("id") Long id) {
        Jugador jugador = Jugador.findJugador(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        if (jugador == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(jugador.toJson(), headers, HttpStatus.OK);
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<Jugador> result = Jugador.findAllJugadors();
        return new ResponseEntity<String>(Jugador.toJsonArray(result), headers, HttpStatus.OK);
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody String json, UriComponentsBuilder uriBuilder) {
        Jugador jugador = Jugador.fromJsonToJugador(json);
        jugador.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        RequestMapping a = (RequestMapping) getClass().getAnnotation(RequestMapping.class);
        headers.add("Location",uriBuilder.path(a.value()[0]+"/"+jugador.getId().toString()).build().toUriString());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/jsonArray", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJsonArray(@RequestBody String json) {
        for (Jugador jugador: Jugador.fromJsonArrayToJugadors(json)) {
            jugador.persist();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody String json, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        Jugador jugador = Jugador.fromJsonToJugador(json);
        jugador.setId(id);
        if (jugador.merge() == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        Jugador jugador = Jugador.findJugador(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (jugador == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        jugador.remove();
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByCorreoELike", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindJugadorsByCorreoELike(@RequestParam("correoE") String correoE) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Jugador.toJsonArray(Jugador.findJugadorsByCorreoELike(correoE).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByCorreoELikeAndStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindJugadorsByCorreoELikeAndStatus(@RequestParam("correoE") String correoE, @RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Jugador.toJsonArray(Jugador.findJugadorsByCorreoELikeAndStatus(correoE, status).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByIdEquipo", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindJugadorsByIdEquipo(@RequestParam("idEquipo") Equipo idEquipo) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Jugador.toJsonArray(Jugador.findJugadorsByIdEquipo(idEquipo).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByIdEquipoAndStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindJugadorsByIdEquipoAndStatus(@RequestParam("idEquipo") Equipo idEquipo, @RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Jugador.toJsonArray(Jugador.findJugadorsByIdEquipoAndStatus(idEquipo, status).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByNombreLikeAndApellidoPaternoLike", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindJugadorsByNombreLikeAndApellidoPaternoLike(@RequestParam("nombre") String nombre, @RequestParam("apellidoPaterno") String apellidoPaterno) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Jugador.toJsonArray(Jugador.findJugadorsByNombreLikeAndApellidoPaternoLike(nombre, apellidoPaterno).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByNombreLikeAndApellidoPaternoLikeAndStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindJugadorsByNombreLikeAndApellidoPaternoLikeAndStatus(@RequestParam("nombre") String nombre, @RequestParam("apellidoPaterno") String apellidoPaterno, @RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Jugador.toJsonArray(Jugador.findJugadorsByNombreLikeAndApellidoPaternoLikeAndStatus(nombre, apellidoPaterno, status).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindJugadorsByStatus(@RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Jugador.toJsonArray(Jugador.findJugadorsByStatus(status).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Jugador jugador, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, jugador);
            return "jugadors/create";
        }
        uiModel.asMap().clear();
        jugador.persist();
        return "redirect:/jugadors/" + encodeUrlPathSegment(jugador.getId().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Jugador());
        return "jugadors/create";
    }

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("jugador", Jugador.findJugador(id));
        uiModel.addAttribute("itemId", id);
        return "jugadors/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("jugadors", Jugador.findJugadorEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Jugador.countJugadors() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("jugadors", Jugador.findAllJugadors(sortFieldName, sortOrder));
        }
        addDateTimeFormatPatterns(uiModel);
        return "jugadors/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Jugador jugador, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, jugador);
            return "jugadors/update";
        }
        uiModel.asMap().clear();
        jugador.merge();
        return "redirect:/jugadors/" + encodeUrlPathSegment(jugador.getId().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, Jugador.findJugador(id));
        return "jugadors/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Jugador jugador = Jugador.findJugador(id);
        jugador.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/jugadors";
    }

	void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("jugador_fechacreacion_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("jugador_fechamodificacion_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }

	void populateEditForm(Model uiModel, Jugador jugador) {
        uiModel.addAttribute("jugador", jugador);
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
