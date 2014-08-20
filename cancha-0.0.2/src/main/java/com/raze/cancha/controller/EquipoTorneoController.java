package com.raze.cancha.controller;
import com.raze.cancha.domain.Equipo;
import com.raze.cancha.domain.EquipoTorneo;
import com.raze.cancha.domain.Torneo;
import com.raze.cancha.reference.Status;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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

@RooWebJson(jsonObject = EquipoTorneo.class)
@Controller
@RequestMapping("/equipotorneos")
@RooWebScaffold(path = "equipotorneos", formBackingObject = EquipoTorneo.class)
@RooWebFinder
public class EquipoTorneoController {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> showJson(@PathVariable("id") Long id) {
        EquipoTorneo equipoTorneo = EquipoTorneo.findEquipoTorneo(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        if (equipoTorneo == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(equipoTorneo.toJson(), headers, HttpStatus.OK);
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<EquipoTorneo> result = EquipoTorneo.findAllEquipoTorneos();
        return new ResponseEntity<String>(EquipoTorneo.toJsonArray(result), headers, HttpStatus.OK);
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody String json, UriComponentsBuilder uriBuilder) {
        EquipoTorneo equipoTorneo = EquipoTorneo.fromJsonToEquipoTorneo(json);
        equipoTorneo.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        RequestMapping a = (RequestMapping) getClass().getAnnotation(RequestMapping.class);
        headers.add("Location",uriBuilder.path(a.value()[0]+"/"+equipoTorneo.getId().toString()).build().toUriString());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/jsonArray", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJsonArray(@RequestBody String json) {
        for (EquipoTorneo equipoTorneo: EquipoTorneo.fromJsonArrayToEquipoTorneos(json)) {
            equipoTorneo.persist();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody String json, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        EquipoTorneo equipoTorneo = EquipoTorneo.fromJsonToEquipoTorneo(json);
        equipoTorneo.setId(id);
        if (equipoTorneo.merge() == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        EquipoTorneo equipoTorneo = EquipoTorneo.findEquipoTorneo(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (equipoTorneo == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        equipoTorneo.remove();
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByIdEquipo", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindEquipoTorneosByIdEquipo(@RequestParam("idEquipo") Equipo idEquipo) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(EquipoTorneo.toJsonArray(EquipoTorneo.findEquipoTorneosByIdEquipo(idEquipo).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByIdEquipoAndStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindEquipoTorneosByIdEquipoAndStatus(@RequestParam("idEquipo") Equipo idEquipo, @RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(EquipoTorneo.toJsonArray(EquipoTorneo.findEquipoTorneosByIdEquipoAndStatus(idEquipo, status).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByIdTorneo", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindEquipoTorneosByIdTorneo(@RequestParam("idTorneo") Torneo idTorneo) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(EquipoTorneo.toJsonArray(EquipoTorneo.findEquipoTorneosByIdTorneo(idTorneo).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByIdTorneoAndStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindEquipoTorneosByIdTorneoAndStatus(@RequestParam("idTorneo") Torneo idTorneo, @RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(EquipoTorneo.toJsonArray(EquipoTorneo.findEquipoTorneosByIdTorneoAndStatus(idTorneo, status).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(params = "find=ByStatus", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindEquipoTorneosByStatus(@RequestParam("status") Status status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(EquipoTorneo.toJsonArray(EquipoTorneo.findEquipoTorneosByStatus(status).getResultList()), headers, HttpStatus.OK);
    }

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid EquipoTorneo equipoTorneo, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, equipoTorneo);
            return "equipotorneos/create";
        }
        uiModel.asMap().clear();
        equipoTorneo.persist();
        return "redirect:/equipotorneos/" + encodeUrlPathSegment(equipoTorneo.getId().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new EquipoTorneo());
        return "equipotorneos/create";
    }

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("equipotorneo", EquipoTorneo.findEquipoTorneo(id));
        uiModel.addAttribute("itemId", id);
        return "equipotorneos/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("equipotorneos", EquipoTorneo.findEquipoTorneoEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) EquipoTorneo.countEquipoTorneos() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("equipotorneos", EquipoTorneo.findAllEquipoTorneos(sortFieldName, sortOrder));
        }
        return "equipotorneos/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid EquipoTorneo equipoTorneo, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, equipoTorneo);
            return "equipotorneos/update";
        }
        uiModel.asMap().clear();
        equipoTorneo.merge();
        return "redirect:/equipotorneos/" + encodeUrlPathSegment(equipoTorneo.getId().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, EquipoTorneo.findEquipoTorneo(id));
        return "equipotorneos/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        EquipoTorneo equipoTorneo = EquipoTorneo.findEquipoTorneo(id);
        equipoTorneo.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/equipotorneos";
    }

	void populateEditForm(Model uiModel, EquipoTorneo equipoTorneo) {
        uiModel.addAttribute("equipoTorneo", equipoTorneo);
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

	@RequestMapping(params = { "find=ByIdEquipo", "form" }, method = RequestMethod.GET)
    public String findEquipoTorneosByIdEquipoForm(Model uiModel) {
        uiModel.addAttribute("equipoes", Equipo.findAllEquipoes());
        return "equipotorneos/findEquipoTorneosByIdEquipo";
    }

	@RequestMapping(params = "find=ByIdEquipo", method = RequestMethod.GET)
    public String findEquipoTorneosByIdEquipo(@RequestParam("idEquipo") Equipo idEquipo, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("equipotorneos", EquipoTorneo.findEquipoTorneosByIdEquipo(idEquipo, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) EquipoTorneo.countFindEquipoTorneosByIdEquipo(idEquipo) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("equipotorneos", EquipoTorneo.findEquipoTorneosByIdEquipo(idEquipo, sortFieldName, sortOrder).getResultList());
        }
        return "equipotorneos/list";
    }

	@RequestMapping(params = { "find=ByIdEquipoAndStatus", "form" }, method = RequestMethod.GET)
    public String findEquipoTorneosByIdEquipoAndStatusForm(Model uiModel) {
        uiModel.addAttribute("equipoes", Equipo.findAllEquipoes());
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        return "equipotorneos/findEquipoTorneosByIdEquipoAndStatus";
    }

	@RequestMapping(params = "find=ByIdEquipoAndStatus", method = RequestMethod.GET)
    public String findEquipoTorneosByIdEquipoAndStatus(@RequestParam("idEquipo") Equipo idEquipo, @RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("equipotorneos", EquipoTorneo.findEquipoTorneosByIdEquipoAndStatus(idEquipo, status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) EquipoTorneo.countFindEquipoTorneosByIdEquipoAndStatus(idEquipo, status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("equipotorneos", EquipoTorneo.findEquipoTorneosByIdEquipoAndStatus(idEquipo, status, sortFieldName, sortOrder).getResultList());
        }
        return "equipotorneos/list";
    }

	@RequestMapping(params = { "find=ByIdTorneo", "form" }, method = RequestMethod.GET)
    public String findEquipoTorneosByIdTorneoForm(Model uiModel) {
        uiModel.addAttribute("torneos", Torneo.findAllTorneos());
        return "equipotorneos/findEquipoTorneosByIdTorneo";
    }

	@RequestMapping(params = "find=ByIdTorneo", method = RequestMethod.GET)
    public String findEquipoTorneosByIdTorneo(@RequestParam("idTorneo") Torneo idTorneo, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("equipotorneos", EquipoTorneo.findEquipoTorneosByIdTorneo(idTorneo, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) EquipoTorneo.countFindEquipoTorneosByIdTorneo(idTorneo) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("equipotorneos", EquipoTorneo.findEquipoTorneosByIdTorneo(idTorneo, sortFieldName, sortOrder).getResultList());
        }
        return "equipotorneos/list";
    }

	@RequestMapping(params = { "find=ByIdTorneoAndStatus", "form" }, method = RequestMethod.GET)
    public String findEquipoTorneosByIdTorneoAndStatusForm(Model uiModel) {
        uiModel.addAttribute("torneos", Torneo.findAllTorneos());
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        return "equipotorneos/findEquipoTorneosByIdTorneoAndStatus";
    }

	@RequestMapping(params = "find=ByIdTorneoAndStatus", method = RequestMethod.GET)
    public String findEquipoTorneosByIdTorneoAndStatus(@RequestParam("idTorneo") Torneo idTorneo, @RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("equipotorneos", EquipoTorneo.findEquipoTorneosByIdTorneoAndStatus(idTorneo, status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) EquipoTorneo.countFindEquipoTorneosByIdTorneoAndStatus(idTorneo, status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("equipotorneos", EquipoTorneo.findEquipoTorneosByIdTorneoAndStatus(idTorneo, status, sortFieldName, sortOrder).getResultList());
        }
        return "equipotorneos/list";
    }

	@RequestMapping(params = { "find=ByStatus", "form" }, method = RequestMethod.GET)
    public String findEquipoTorneosByStatusForm(Model uiModel) {
        uiModel.addAttribute("statuses", Status.findAllStatuses());
        return "equipotorneos/findEquipoTorneosByStatus";
    }

	@RequestMapping(params = "find=ByStatus", method = RequestMethod.GET)
    public String findEquipoTorneosByStatus(@RequestParam("status") Status status, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("equipotorneos", EquipoTorneo.findEquipoTorneosByStatus(status, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) EquipoTorneo.countFindEquipoTorneosByStatus(status) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("equipotorneos", EquipoTorneo.findEquipoTorneosByStatus(status, sortFieldName, sortOrder).getResultList());
        }
        return "equipotorneos/list";
    }
}
