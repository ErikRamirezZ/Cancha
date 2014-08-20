package com.raze.cancha.controller;
import com.raze.cancha.domain.Arbitro;
import com.raze.cancha.domain.Cancha;
import com.raze.cancha.domain.Equipo;
import com.raze.cancha.domain.Partido;
import com.raze.cancha.domain.Torneo;
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

@RooWebJson(jsonObject = Partido.class)
@Controller
@RequestMapping("/partidoes")
@RooWebScaffold(path = "partidoes", formBackingObject = Partido.class)
@RooWebFinder
public class PartidoController {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> showJson(@PathVariable("id") Long id) {
        Partido partido = Partido.findPartido(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        if (partido == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(partido.toJson(), headers, HttpStatus.OK);
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<Partido> result = Partido.findAllPartidoes();
        return new ResponseEntity<String>(Partido.toJsonArray(result), headers, HttpStatus.OK);
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody String json, UriComponentsBuilder uriBuilder) {
        Partido partido = Partido.fromJsonToPartido(json);
        partido.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        RequestMapping a = (RequestMapping) getClass().getAnnotation(RequestMapping.class);
        headers.add("Location",uriBuilder.path(a.value()[0]+"/"+partido.getId().toString()).build().toUriString());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/jsonArray", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJsonArray(@RequestBody String json) {
        for (Partido partido: Partido.fromJsonArrayToPartidoes(json)) {
            partido.persist();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody String json, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        Partido partido = Partido.fromJsonToPartido(json);
        partido.setId(id);
        if (partido.merge() == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        Partido partido = Partido.findPartido(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (partido == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        partido.remove();
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByFechaJuegoBetween", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindPartidoesByFechaJuegoBetween(@RequestParam("minFechaJuego") @DateTimeFormat(style = "M-") Date minFechaJuego, @RequestParam("maxFechaJuego") @DateTimeFormat(style = "M-") Date maxFechaJuego) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Partido.toJsonArray(Partido.findPartidoesByFechaJuegoBetween(minFechaJuego, maxFechaJuego).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByFechaJuegoBetweenAndStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindPartidoesByFechaJuegoBetweenAndStatus(@RequestParam("minFechaJuego") @DateTimeFormat(style = "M-") Date minFechaJuego, @RequestParam("maxFechaJuego") @DateTimeFormat(style = "M-") Date maxFechaJuego, @RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Partido.toJsonArray(Partido.findPartidoesByFechaJuegoBetweenAndStatus(minFechaJuego, maxFechaJuego, status).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByFechaJuegoEquals", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindPartidoesByFechaJuegoEquals(@RequestParam("fechaJuego") @DateTimeFormat(style = "M-") Date fechaJuego) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Partido.toJsonArray(Partido.findPartidoesByFechaJuegoEquals(fechaJuego).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByIdArbitro", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindPartidoesByIdArbitro(@RequestParam("idArbitro") Arbitro idArbitro) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Partido.toJsonArray(Partido.findPartidoesByIdArbitro(idArbitro).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByIdCancha", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindPartidoesByIdCancha(@RequestParam("idCancha") Cancha idCancha) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Partido.toJsonArray(Partido.findPartidoesByIdCancha(idCancha).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByIdCanchaAndStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindPartidoesByIdCanchaAndStatus(@RequestParam("idCancha") Cancha idCancha, @RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Partido.toJsonArray(Partido.findPartidoesByIdCanchaAndStatus(idCancha, status).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByIdTorneoEquipoLocal", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindPartidoesByIdTorneoEquipoLocal(@RequestParam("idTorneoEquipoLocal") Torneo idTorneoEquipoLocal) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Partido.toJsonArray(Partido.findPartidoesByIdTorneoEquipoLocal(idTorneoEquipoLocal).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByIdTorneoEquipoLocalAndIdTorneoEquipoVisitante", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindPartidoesByIdTorneoEquipoLocalAndIdTorneoEquipoVisitante(@RequestParam("idTorneoEquipoLocal") Torneo idTorneoEquipoLocal, @RequestParam("idTorneoEquipoVisitante") Torneo idTorneoEquipoVisitante) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Partido.toJsonArray(Partido.findPartidoesByIdTorneoEquipoLocalAndIdTorneoEquipoVisitante(idTorneoEquipoLocal, idTorneoEquipoVisitante).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByIdTorneoEquipoLocalAndIdTorneoEquipoVisitanteAndStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindPartidoesByIdTorneoEquipoLocalAndIdTorneoEquipoVisitanteAndStatus(@RequestParam("idTorneoEquipoLocal") Torneo idTorneoEquipoLocal, @RequestParam("idTorneoEquipoVisitante") Torneo idTorneoEquipoVisitante, @RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Partido.toJsonArray(Partido.findPartidoesByIdTorneoEquipoLocalAndIdTorneoEquipoVisitanteAndStatus(idTorneoEquipoLocal, idTorneoEquipoVisitante, status).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByIdTorneoEquipoLocalAndStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindPartidoesByIdTorneoEquipoLocalAndStatus(@RequestParam("idTorneoEquipoLocal") Torneo idTorneoEquipoLocal, @RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Partido.toJsonArray(Partido.findPartidoesByIdTorneoEquipoLocalAndStatus(idTorneoEquipoLocal, status).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByIdTorneoEquipoVisitante", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindPartidoesByIdTorneoEquipoVisitante(@RequestParam("idTorneoEquipoVisitante") Torneo idTorneoEquipoVisitante) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Partido.toJsonArray(Partido.findPartidoesByIdTorneoEquipoVisitante(idTorneoEquipoVisitante).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByIdTorneoEquipoVisitanteAndIdEquipoLocal", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindPartidoesByIdTorneoEquipoVisitanteAndIdEquipoLocal(@RequestParam("idTorneoEquipoVisitante") Torneo idTorneoEquipoVisitante, @RequestParam("idEquipoLocal") Equipo idEquipoLocal) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Partido.toJsonArray(Partido.findPartidoesByIdTorneoEquipoVisitanteAndIdEquipoLocal(idTorneoEquipoVisitante, idEquipoLocal).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByIdTorneoEquipoVisitanteAndIdEquipoLocalAndStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindPartidoesByIdTorneoEquipoVisitanteAndIdEquipoLocalAndStatus(@RequestParam("idTorneoEquipoVisitante") Torneo idTorneoEquipoVisitante, @RequestParam("idEquipoLocal") Equipo idEquipoLocal, @RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Partido.toJsonArray(Partido.findPartidoesByIdTorneoEquipoVisitanteAndIdEquipoLocalAndStatus(idTorneoEquipoVisitante, idEquipoLocal, status).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByIdTorneoEquipoVisitanteAndIdEquipoVisitante", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindPartidoesByIdTorneoEquipoVisitanteAndIdEquipoVisitante(@RequestParam("idTorneoEquipoVisitante") Torneo idTorneoEquipoVisitante, @RequestParam("idEquipoVisitante") Equipo idEquipoVisitante) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Partido.toJsonArray(Partido.findPartidoesByIdTorneoEquipoVisitanteAndIdEquipoVisitante(idTorneoEquipoVisitante, idEquipoVisitante).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByIdTorneoEquipoVisitanteAndIdEquipoVisitanteAndStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindPartidoesByIdTorneoEquipoVisitanteAndIdEquipoVisitanteAndStatus(@RequestParam("idTorneoEquipoVisitante") Torneo idTorneoEquipoVisitante, @RequestParam("idEquipoVisitante") Equipo idEquipoVisitante, @RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Partido.toJsonArray(Partido.findPartidoesByIdTorneoEquipoVisitanteAndIdEquipoVisitanteAndStatus(idTorneoEquipoVisitante, idEquipoVisitante, status).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByIdTorneoEquipoVisitanteAndStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindPartidoesByIdTorneoEquipoVisitanteAndStatus(@RequestParam("idTorneoEquipoVisitante") Torneo idTorneoEquipoVisitante, @RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Partido.toJsonArray(Partido.findPartidoesByIdTorneoEquipoVisitanteAndStatus(idTorneoEquipoVisitante, status).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindPartidoesByStatus(@RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(Partido.toJsonArray(Partido.findPartidoesByStatus(status).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = { "find=ByFechaJuegoBetween", "form" }, method = RequestMethod.GET)
    public String findPartidoesByFechaJuegoBetweenForm(Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        return "partidoes/findPartidoesByFechaJuegoBetween";
    }

	@RequestMapping(params = "find=ByFechaJuegoBetween", method = RequestMethod.GET)
    public String findPartidoesByFechaJuegoBetween(@RequestParam("minFechaJuego") @DateTimeFormat(style = "M-") Date minFechaJuego, @RequestParam("maxFechaJuego") @DateTimeFormat(style = "M-") Date maxFechaJuego, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("partidoes", Partido.findPartidoesByFechaJuegoBetween(minFechaJuego, maxFechaJuego, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Partido.countFindPartidoesByFechaJuegoBetween(minFechaJuego, maxFechaJuego) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("partidoes", Partido.findPartidoesByFechaJuegoBetween(minFechaJuego, maxFechaJuego, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "partidoes/list";
    }

	@RequestMapping(params = { "find=ByFechaJuegoBetweenAndStatus", "form" }, method = RequestMethod.GET)
    public String findPartidoesByFechaJuegoBetweenAndStatusForm(Model uiModel) {
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        addDateTimeFormatPatterns(uiModel);
        return "partidoes/findPartidoesByFechaJuegoBetweenAndStatus";
    }

	@RequestMapping(params = "find=ByFechaJuegoBetweenAndStatus", method = RequestMethod.GET)
    public String findPartidoesByFechaJuegoBetweenAndStatus(@RequestParam("minFechaJuego") @DateTimeFormat(style = "M-") Date minFechaJuego, @RequestParam("maxFechaJuego") @DateTimeFormat(style = "M-") Date maxFechaJuego, @RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("partidoes", Partido.findPartidoesByFechaJuegoBetweenAndStatus(minFechaJuego, maxFechaJuego, status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Partido.countFindPartidoesByFechaJuegoBetweenAndStatus(minFechaJuego, maxFechaJuego, status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("partidoes", Partido.findPartidoesByFechaJuegoBetweenAndStatus(minFechaJuego, maxFechaJuego, status, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "partidoes/list";
    }

	@RequestMapping(params = { "find=ByFechaJuegoEquals", "form" }, method = RequestMethod.GET)
    public String findPartidoesByFechaJuegoEqualsForm(Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        return "partidoes/findPartidoesByFechaJuegoEquals";
    }

	@RequestMapping(params = "find=ByFechaJuegoEquals", method = RequestMethod.GET)
    public String findPartidoesByFechaJuegoEquals(@RequestParam("fechaJuego") @DateTimeFormat(style = "M-") Date fechaJuego, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("partidoes", Partido.findPartidoesByFechaJuegoEquals(fechaJuego, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Partido.countFindPartidoesByFechaJuegoEquals(fechaJuego) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("partidoes", Partido.findPartidoesByFechaJuegoEquals(fechaJuego, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "partidoes/list";
    }

	@RequestMapping(params = { "find=ByIdArbitro", "form" }, method = RequestMethod.GET)
    public String findPartidoesByIdArbitroForm(Model uiModel) {
        uiModel.addAttribute("arbitroes", Arbitro.findAllArbitroes());
        return "partidoes/findPartidoesByIdArbitro";
    }

	@RequestMapping(params = "find=ByIdArbitro", method = RequestMethod.GET)
    public String findPartidoesByIdArbitro(@RequestParam("idArbitro") Arbitro idArbitro, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("partidoes", Partido.findPartidoesByIdArbitro(idArbitro, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Partido.countFindPartidoesByIdArbitro(idArbitro) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("partidoes", Partido.findPartidoesByIdArbitro(idArbitro, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "partidoes/list";
    }

	@RequestMapping(params = { "find=ByIdCancha", "form" }, method = RequestMethod.GET)
    public String findPartidoesByIdCanchaForm(Model uiModel) {
        uiModel.addAttribute("canchas", Cancha.findAllCanchas());
        return "partidoes/findPartidoesByIdCancha";
    }

	@RequestMapping(params = "find=ByIdCancha", method = RequestMethod.GET)
    public String findPartidoesByIdCancha(@RequestParam("idCancha") Cancha idCancha, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("partidoes", Partido.findPartidoesByIdCancha(idCancha, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Partido.countFindPartidoesByIdCancha(idCancha) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("partidoes", Partido.findPartidoesByIdCancha(idCancha, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "partidoes/list";
    }

	@RequestMapping(params = { "find=ByIdCanchaAndStatus", "form" }, method = RequestMethod.GET)
    public String findPartidoesByIdCanchaAndStatusForm(Model uiModel) {
        uiModel.addAttribute("canchas", Cancha.findAllCanchas());
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        return "partidoes/findPartidoesByIdCanchaAndStatus";
    }

	@RequestMapping(params = "find=ByIdCanchaAndStatus", method = RequestMethod.GET)
    public String findPartidoesByIdCanchaAndStatus(@RequestParam("idCancha") Cancha idCancha, @RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("partidoes", Partido.findPartidoesByIdCanchaAndStatus(idCancha, status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Partido.countFindPartidoesByIdCanchaAndStatus(idCancha, status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("partidoes", Partido.findPartidoesByIdCanchaAndStatus(idCancha, status, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "partidoes/list";
    }

	@RequestMapping(params = { "find=ByIdTorneoEquipoLocal", "form" }, method = RequestMethod.GET)
    public String findPartidoesByIdTorneoEquipoLocalForm(Model uiModel) {
        uiModel.addAttribute("torneos", Torneo.findAllTorneos());
        return "partidoes/findPartidoesByIdTorneoEquipoLocal";
    }

	@RequestMapping(params = "find=ByIdTorneoEquipoLocal", method = RequestMethod.GET)
    public String findPartidoesByIdTorneoEquipoLocal(@RequestParam("idTorneoEquipoLocal") Torneo idTorneoEquipoLocal, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("partidoes", Partido.findPartidoesByIdTorneoEquipoLocal(idTorneoEquipoLocal, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Partido.countFindPartidoesByIdTorneoEquipoLocal(idTorneoEquipoLocal) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("partidoes", Partido.findPartidoesByIdTorneoEquipoLocal(idTorneoEquipoLocal, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "partidoes/list";
    }

	@RequestMapping(params = { "find=ByIdTorneoEquipoLocalAndIdTorneoEquipoVisitante", "form" }, method = RequestMethod.GET)
    public String findPartidoesByIdTorneoEquipoLocalAndIdTorneoEquipoVisitanteForm(Model uiModel) {
        uiModel.addAttribute("torneos", Torneo.findAllTorneos());
        uiModel.addAttribute("torneos", Torneo.findAllTorneos());
        return "partidoes/findPartidoesByIdTorneoEquipoLocalAndIdTorneoEquipoVisitante";
    }

	@RequestMapping(params = "find=ByIdTorneoEquipoLocalAndIdTorneoEquipoVisitante", method = RequestMethod.GET)
    public String findPartidoesByIdTorneoEquipoLocalAndIdTorneoEquipoVisitante(@RequestParam("idTorneoEquipoLocal") Torneo idTorneoEquipoLocal, @RequestParam("idTorneoEquipoVisitante") Torneo idTorneoEquipoVisitante, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("partidoes", Partido.findPartidoesByIdTorneoEquipoLocalAndIdTorneoEquipoVisitante(idTorneoEquipoLocal, idTorneoEquipoVisitante, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Partido.countFindPartidoesByIdTorneoEquipoLocalAndIdTorneoEquipoVisitante(idTorneoEquipoLocal, idTorneoEquipoVisitante) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("partidoes", Partido.findPartidoesByIdTorneoEquipoLocalAndIdTorneoEquipoVisitante(idTorneoEquipoLocal, idTorneoEquipoVisitante, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "partidoes/list";
    }

	@RequestMapping(params = { "find=ByIdTorneoEquipoLocalAndIdTorneoEquipoVisitanteAndStatus", "form" }, method = RequestMethod.GET)
    public String findPartidoesByIdTorneoEquipoLocalAndIdTorneoEquipoVisitanteAndStatusForm(Model uiModel) {
        uiModel.addAttribute("torneos", Torneo.findAllTorneos());
        uiModel.addAttribute("torneos", Torneo.findAllTorneos());
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        return "partidoes/findPartidoesByIdTorneoEquipoLocalAndIdTorneoEquipoVisitanteAndStatus";
    }

	@RequestMapping(params = "find=ByIdTorneoEquipoLocalAndIdTorneoEquipoVisitanteAndStatus", method = RequestMethod.GET)
    public String findPartidoesByIdTorneoEquipoLocalAndIdTorneoEquipoVisitanteAndStatus(@RequestParam("idTorneoEquipoLocal") Torneo idTorneoEquipoLocal, @RequestParam("idTorneoEquipoVisitante") Torneo idTorneoEquipoVisitante, @RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("partidoes", Partido.findPartidoesByIdTorneoEquipoLocalAndIdTorneoEquipoVisitanteAndStatus(idTorneoEquipoLocal, idTorneoEquipoVisitante, status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Partido.countFindPartidoesByIdTorneoEquipoLocalAndIdTorneoEquipoVisitanteAndStatus(idTorneoEquipoLocal, idTorneoEquipoVisitante, status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("partidoes", Partido.findPartidoesByIdTorneoEquipoLocalAndIdTorneoEquipoVisitanteAndStatus(idTorneoEquipoLocal, idTorneoEquipoVisitante, status, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "partidoes/list";
    }

	@RequestMapping(params = { "find=ByIdTorneoEquipoLocalAndStatus", "form" }, method = RequestMethod.GET)
    public String findPartidoesByIdTorneoEquipoLocalAndStatusForm(Model uiModel) {
        uiModel.addAttribute("torneos", Torneo.findAllTorneos());
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        return "partidoes/findPartidoesByIdTorneoEquipoLocalAndStatus";
    }

	@RequestMapping(params = "find=ByIdTorneoEquipoLocalAndStatus", method = RequestMethod.GET)
    public String findPartidoesByIdTorneoEquipoLocalAndStatus(@RequestParam("idTorneoEquipoLocal") Torneo idTorneoEquipoLocal, @RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("partidoes", Partido.findPartidoesByIdTorneoEquipoLocalAndStatus(idTorneoEquipoLocal, status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Partido.countFindPartidoesByIdTorneoEquipoLocalAndStatus(idTorneoEquipoLocal, status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("partidoes", Partido.findPartidoesByIdTorneoEquipoLocalAndStatus(idTorneoEquipoLocal, status, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "partidoes/list";
    }

	@RequestMapping(params = { "find=ByIdTorneoEquipoVisitante", "form" }, method = RequestMethod.GET)
    public String findPartidoesByIdTorneoEquipoVisitanteForm(Model uiModel) {
        uiModel.addAttribute("torneos", Torneo.findAllTorneos());
        return "partidoes/findPartidoesByIdTorneoEquipoVisitante";
    }

	@RequestMapping(params = "find=ByIdTorneoEquipoVisitante", method = RequestMethod.GET)
    public String findPartidoesByIdTorneoEquipoVisitante(@RequestParam("idTorneoEquipoVisitante") Torneo idTorneoEquipoVisitante, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("partidoes", Partido.findPartidoesByIdTorneoEquipoVisitante(idTorneoEquipoVisitante, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Partido.countFindPartidoesByIdTorneoEquipoVisitante(idTorneoEquipoVisitante) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("partidoes", Partido.findPartidoesByIdTorneoEquipoVisitante(idTorneoEquipoVisitante, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "partidoes/list";
    }

	@RequestMapping(params = { "find=ByIdTorneoEquipoVisitanteAndIdEquipoLocal", "form" }, method = RequestMethod.GET)
    public String findPartidoesByIdTorneoEquipoVisitanteAndIdEquipoLocalForm(Model uiModel) {
        uiModel.addAttribute("torneos", Torneo.findAllTorneos());
        uiModel.addAttribute("equipoes", Equipo.findAllEquipoes());
        return "partidoes/findPartidoesByIdTorneoEquipoVisitanteAndIdEquipoLocal";
    }

	@RequestMapping(params = "find=ByIdTorneoEquipoVisitanteAndIdEquipoLocal", method = RequestMethod.GET)
    public String findPartidoesByIdTorneoEquipoVisitanteAndIdEquipoLocal(@RequestParam("idTorneoEquipoVisitante") Torneo idTorneoEquipoVisitante, @RequestParam("idEquipoLocal") Equipo idEquipoLocal, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("partidoes", Partido.findPartidoesByIdTorneoEquipoVisitanteAndIdEquipoLocal(idTorneoEquipoVisitante, idEquipoLocal, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Partido.countFindPartidoesByIdTorneoEquipoVisitanteAndIdEquipoLocal(idTorneoEquipoVisitante, idEquipoLocal) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("partidoes", Partido.findPartidoesByIdTorneoEquipoVisitanteAndIdEquipoLocal(idTorneoEquipoVisitante, idEquipoLocal, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "partidoes/list";
    }

	@RequestMapping(params = { "find=ByIdTorneoEquipoVisitanteAndIdEquipoLocalAndStatus", "form" }, method = RequestMethod.GET)
    public String findPartidoesByIdTorneoEquipoVisitanteAndIdEquipoLocalAndStatusForm(Model uiModel) {
        uiModel.addAttribute("torneos", Torneo.findAllTorneos());
        uiModel.addAttribute("equipoes", Equipo.findAllEquipoes());
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        return "partidoes/findPartidoesByIdTorneoEquipoVisitanteAndIdEquipoLocalAndStatus";
    }

	@RequestMapping(params = "find=ByIdTorneoEquipoVisitanteAndIdEquipoLocalAndStatus", method = RequestMethod.GET)
    public String findPartidoesByIdTorneoEquipoVisitanteAndIdEquipoLocalAndStatus(@RequestParam("idTorneoEquipoVisitante") Torneo idTorneoEquipoVisitante, @RequestParam("idEquipoLocal") Equipo idEquipoLocal, @RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("partidoes", Partido.findPartidoesByIdTorneoEquipoVisitanteAndIdEquipoLocalAndStatus(idTorneoEquipoVisitante, idEquipoLocal, status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Partido.countFindPartidoesByIdTorneoEquipoVisitanteAndIdEquipoLocalAndStatus(idTorneoEquipoVisitante, idEquipoLocal, status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("partidoes", Partido.findPartidoesByIdTorneoEquipoVisitanteAndIdEquipoLocalAndStatus(idTorneoEquipoVisitante, idEquipoLocal, status, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "partidoes/list";
    }

	@RequestMapping(params = { "find=ByIdTorneoEquipoVisitanteAndIdEquipoVisitante", "form" }, method = RequestMethod.GET)
    public String findPartidoesByIdTorneoEquipoVisitanteAndIdEquipoVisitanteForm(Model uiModel) {
        uiModel.addAttribute("torneos", Torneo.findAllTorneos());
        uiModel.addAttribute("equipoes", Equipo.findAllEquipoes());
        return "partidoes/findPartidoesByIdTorneoEquipoVisitanteAndIdEquipoVisitante";
    }

	@RequestMapping(params = "find=ByIdTorneoEquipoVisitanteAndIdEquipoVisitante", method = RequestMethod.GET)
    public String findPartidoesByIdTorneoEquipoVisitanteAndIdEquipoVisitante(@RequestParam("idTorneoEquipoVisitante") Torneo idTorneoEquipoVisitante, @RequestParam("idEquipoVisitante") Equipo idEquipoVisitante, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("partidoes", Partido.findPartidoesByIdTorneoEquipoVisitanteAndIdEquipoVisitante(idTorneoEquipoVisitante, idEquipoVisitante, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Partido.countFindPartidoesByIdTorneoEquipoVisitanteAndIdEquipoVisitante(idTorneoEquipoVisitante, idEquipoVisitante) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("partidoes", Partido.findPartidoesByIdTorneoEquipoVisitanteAndIdEquipoVisitante(idTorneoEquipoVisitante, idEquipoVisitante, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "partidoes/list";
    }

	@RequestMapping(params = { "find=ByIdTorneoEquipoVisitanteAndIdEquipoVisitanteAndStatus", "form" }, method = RequestMethod.GET)
    public String findPartidoesByIdTorneoEquipoVisitanteAndIdEquipoVisitanteAndStatusForm(Model uiModel) {
        uiModel.addAttribute("torneos", Torneo.findAllTorneos());
        uiModel.addAttribute("equipoes", Equipo.findAllEquipoes());
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        return "partidoes/findPartidoesByIdTorneoEquipoVisitanteAndIdEquipoVisitanteAndStatus";
    }

	@RequestMapping(params = "find=ByIdTorneoEquipoVisitanteAndIdEquipoVisitanteAndStatus", method = RequestMethod.GET)
    public String findPartidoesByIdTorneoEquipoVisitanteAndIdEquipoVisitanteAndStatus(@RequestParam("idTorneoEquipoVisitante") Torneo idTorneoEquipoVisitante, @RequestParam("idEquipoVisitante") Equipo idEquipoVisitante, @RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("partidoes", Partido.findPartidoesByIdTorneoEquipoVisitanteAndIdEquipoVisitanteAndStatus(idTorneoEquipoVisitante, idEquipoVisitante, status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Partido.countFindPartidoesByIdTorneoEquipoVisitanteAndIdEquipoVisitanteAndStatus(idTorneoEquipoVisitante, idEquipoVisitante, status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("partidoes", Partido.findPartidoesByIdTorneoEquipoVisitanteAndIdEquipoVisitanteAndStatus(idTorneoEquipoVisitante, idEquipoVisitante, status, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "partidoes/list";
    }

	@RequestMapping(params = { "find=ByIdTorneoEquipoVisitanteAndStatus", "form" }, method = RequestMethod.GET)
    public String findPartidoesByIdTorneoEquipoVisitanteAndStatusForm(Model uiModel) {
        uiModel.addAttribute("torneos", Torneo.findAllTorneos());
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        return "partidoes/findPartidoesByIdTorneoEquipoVisitanteAndStatus";
    }

	@RequestMapping(params = "find=ByIdTorneoEquipoVisitanteAndStatus", method = RequestMethod.GET)
    public String findPartidoesByIdTorneoEquipoVisitanteAndStatus(@RequestParam("idTorneoEquipoVisitante") Torneo idTorneoEquipoVisitante, @RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("partidoes", Partido.findPartidoesByIdTorneoEquipoVisitanteAndStatus(idTorneoEquipoVisitante, status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Partido.countFindPartidoesByIdTorneoEquipoVisitanteAndStatus(idTorneoEquipoVisitante, status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("partidoes", Partido.findPartidoesByIdTorneoEquipoVisitanteAndStatus(idTorneoEquipoVisitante, status, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "partidoes/list";
    }

	@RequestMapping(params = { "find=ByStatus", "form" }, method = RequestMethod.GET)
    public String findPartidoesByStatusForm(Model uiModel) {
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        return "partidoes/findPartidoesByStatus";
    }

	@RequestMapping(params = "find=ByStatus", method = RequestMethod.GET)
    public String findPartidoesByStatus(@RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("partidoes", Partido.findPartidoesByStatus(status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Partido.countFindPartidoesByStatus(status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("partidoes", Partido.findPartidoesByStatus(status, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "partidoes/list";
    }

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Partido partido, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, partido);
            return "partidoes/create";
        }
        uiModel.asMap().clear();
        partido.persist();
        return "redirect:/partidoes/" + encodeUrlPathSegment(partido.getId().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Partido());
        return "partidoes/create";
    }

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("partido", Partido.findPartido(id));
        uiModel.addAttribute("itemId", id);
        return "partidoes/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("partidoes", Partido.findPartidoEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Partido.countPartidoes() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("partidoes", Partido.findAllPartidoes(sortFieldName, sortOrder));
        }
        addDateTimeFormatPatterns(uiModel);
        return "partidoes/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Partido partido, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, partido);
            return "partidoes/update";
        }
        uiModel.asMap().clear();
        partido.merge();
        return "redirect:/partidoes/" + encodeUrlPathSegment(partido.getId().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, Partido.findPartido(id));
        return "partidoes/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Partido partido = Partido.findPartido(id);
        partido.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/partidoes";
    }

	void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("partido_fechajuego_date_format", org.joda.time.format.DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("partido_fechacreacion_date_format", org.joda.time.format.DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("partido_fechamodificacion_date_format", org.joda.time.format.DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }

	void populateEditForm(Model uiModel, Partido partido) {
        uiModel.addAttribute("partido", partido);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("arbitroes", Arbitro.findAllArbitroes());
        uiModel.addAttribute("canchas", Cancha.findAllCanchas());
        uiModel.addAttribute("equipoes", Equipo.findAllEquipoes());
        uiModel.addAttribute("torneos", Torneo.findAllTorneos());
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
