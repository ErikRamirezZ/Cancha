package com.raze.cancha.controller;

import com.raze.cancha.domain.Alineacion;
import com.raze.cancha.domain.Arbitro;
import com.raze.cancha.domain.Cancha;
import com.raze.cancha.domain.Delegado;
import com.raze.cancha.domain.Empresa;
import com.raze.cancha.domain.Equipo;
import com.raze.cancha.domain.EquipoTorneo;
import com.raze.cancha.domain.EstadisticasPartido;
import com.raze.cancha.domain.Incidencia;
import com.raze.cancha.domain.Jugador;
import com.raze.cancha.domain.Pago;
import com.raze.cancha.domain.Partido;
import com.raze.cancha.domain.Sucursal;
import com.raze.cancha.domain.Torneo;
import com.raze.cancha.domain.Usuario;
import com.raze.cancha.reference.Concepto;
import com.raze.cancha.reference.Modulo;
import com.raze.cancha.reference.Status;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.roo.addon.web.mvc.controller.converter.RooConversionService;

@Configurable
/**
 * A central place to register application converters and formatters. 
 */
@RooConversionService
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

	@Override
	protected void installFormatters(FormatterRegistry registry) {
		super.installFormatters(registry);
		// Register application converters and formatters
	}

	public Converter<Alineacion, String> getAlineacionToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.raze.cancha.domain.Alineacion, java.lang.String>() {
            public String convert(Alineacion alineacion) {
                return "(no displayable fields)";
            }
        };
    }

	public Converter<Long, Alineacion> getIdToAlineacionConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.raze.cancha.domain.Alineacion>() {
            public com.raze.cancha.domain.Alineacion convert(java.lang.Long id) {
                return Alineacion.findAlineacion(id);
            }
        };
    }

	public Converter<String, Alineacion> getStringToAlineacionConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.raze.cancha.domain.Alineacion>() {
            public com.raze.cancha.domain.Alineacion convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Alineacion.class);
            }
        };
    }

	public Converter<Arbitro, String> getArbitroToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.raze.cancha.domain.Arbitro, java.lang.String>() {
            public String convert(Arbitro arbitro) {
                return new StringBuilder().append(arbitro.getNombre()).append(' ').append(arbitro.getApellidoPaterno()).append(' ').append(arbitro.getApellidoMaterno()).append(' ').append(arbitro.getEdad()).toString();
            }
        };
    }

	public Converter<Long, Arbitro> getIdToArbitroConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.raze.cancha.domain.Arbitro>() {
            public com.raze.cancha.domain.Arbitro convert(java.lang.Long id) {
                return Arbitro.findArbitro(id);
            }
        };
    }

	public Converter<String, Arbitro> getStringToArbitroConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.raze.cancha.domain.Arbitro>() {
            public com.raze.cancha.domain.Arbitro convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Arbitro.class);
            }
        };
    }

	public Converter<Cancha, String> getCanchaToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.raze.cancha.domain.Cancha, java.lang.String>() {
            public String convert(Cancha cancha) {
                return new StringBuilder().append(cancha.getNombre()).append(' ').append(cancha.getDescripcion()).append(' ').append(cancha.getFechaCreacion()).append(' ').append(cancha.getFechaModificacion()).toString();
            }
        };
    }

	public Converter<Long, Cancha> getIdToCanchaConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.raze.cancha.domain.Cancha>() {
            public com.raze.cancha.domain.Cancha convert(java.lang.Long id) {
                return Cancha.findCancha(id);
            }
        };
    }

	public Converter<String, Cancha> getStringToCanchaConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.raze.cancha.domain.Cancha>() {
            public com.raze.cancha.domain.Cancha convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Cancha.class);
            }
        };
    }

	public Converter<Delegado, String> getDelegadoToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.raze.cancha.domain.Delegado, java.lang.String>() {
            public String convert(Delegado delegado) {
                return new StringBuilder().append(delegado.getNombre()).append(' ').append(delegado.getApellidoPaterno()).append(' ').append(delegado.getApellidoMaterno()).append(' ').append(delegado.getDomicilio()).toString();
            }
        };
    }

	public Converter<Long, Delegado> getIdToDelegadoConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.raze.cancha.domain.Delegado>() {
            public com.raze.cancha.domain.Delegado convert(java.lang.Long id) {
                return Delegado.findDelegado(id);
            }
        };
    }

	public Converter<String, Delegado> getStringToDelegadoConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.raze.cancha.domain.Delegado>() {
            public com.raze.cancha.domain.Delegado convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Delegado.class);
            }
        };
    }

	public Converter<Empresa, String> getEmpresaToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.raze.cancha.domain.Empresa, java.lang.String>() {
            public String convert(Empresa empresa) {
                return new StringBuilder().append(empresa.getNombre()).append(' ').append(empresa.getNombreComercial()).append(' ').append(empresa.getDomicilio()).append(' ').append(empresa.getTelefono()).toString();
            }
        };
    }

	public Converter<Long, Empresa> getIdToEmpresaConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.raze.cancha.domain.Empresa>() {
            public com.raze.cancha.domain.Empresa convert(java.lang.Long id) {
                return Empresa.findEmpresa(id);
            }
        };
    }

	public Converter<String, Empresa> getStringToEmpresaConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.raze.cancha.domain.Empresa>() {
            public com.raze.cancha.domain.Empresa convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Empresa.class);
            }
        };
    }

	public Converter<Equipo, String> getEquipoToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.raze.cancha.domain.Equipo, java.lang.String>() {
            public String convert(Equipo equipo) {
                return new StringBuilder().append(equipo.getNombre()).append(' ').append(equipo.getNombreCorto()).append(' ').append(equipo.getNombreLargo()).append(' ').append(equipo.getFechaCreacion()).toString();
            }
        };
    }

	public Converter<Long, Equipo> getIdToEquipoConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.raze.cancha.domain.Equipo>() {
            public com.raze.cancha.domain.Equipo convert(java.lang.Long id) {
                return Equipo.findEquipo(id);
            }
        };
    }

	public Converter<String, Equipo> getStringToEquipoConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.raze.cancha.domain.Equipo>() {
            public com.raze.cancha.domain.Equipo convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Equipo.class);
            }
        };
    }

	public Converter<EquipoTorneo, String> getEquipoTorneoToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.raze.cancha.domain.EquipoTorneo, java.lang.String>() {
            public String convert(EquipoTorneo equipoTorneo) {
                return "(no displayable fields)";
            }
        };
    }

	public Converter<Long, EquipoTorneo> getIdToEquipoTorneoConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.raze.cancha.domain.EquipoTorneo>() {
            public com.raze.cancha.domain.EquipoTorneo convert(java.lang.Long id) {
                return EquipoTorneo.findEquipoTorneo(id);
            }
        };
    }

	public Converter<String, EquipoTorneo> getStringToEquipoTorneoConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.raze.cancha.domain.EquipoTorneo>() {
            public com.raze.cancha.domain.EquipoTorneo convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), EquipoTorneo.class);
            }
        };
    }

	public Converter<EstadisticasPartido, String> getEstadisticasPartidoToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.raze.cancha.domain.EstadisticasPartido, java.lang.String>() {
            public String convert(EstadisticasPartido estadisticasPartido) {
                return new StringBuilder().append(estadisticasPartido.getMarcadorEquipoLocal()).append(' ').append(estadisticasPartido.getMarcadorEquipoVisitante()).append(' ').append(estadisticasPartido.getObservaciones()).append(' ').append(estadisticasPartido.getFechaCreacion()).toString();
            }
        };
    }

	public Converter<Long, EstadisticasPartido> getIdToEstadisticasPartidoConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.raze.cancha.domain.EstadisticasPartido>() {
            public com.raze.cancha.domain.EstadisticasPartido convert(java.lang.Long id) {
                return EstadisticasPartido.findEstadisticasPartido(id);
            }
        };
    }

	public Converter<String, EstadisticasPartido> getStringToEstadisticasPartidoConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.raze.cancha.domain.EstadisticasPartido>() {
            public com.raze.cancha.domain.EstadisticasPartido convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), EstadisticasPartido.class);
            }
        };
    }

	public Converter<Incidencia, String> getIncidenciaToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.raze.cancha.domain.Incidencia, java.lang.String>() {
            public String convert(Incidencia incidencia) {
                return new StringBuilder().append(incidencia.getObservaciones()).append(' ').append(incidencia.getFechaCreacion()).append(' ').append(incidencia.getFechaModificacion()).toString();
            }
        };
    }

	public Converter<Long, Incidencia> getIdToIncidenciaConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.raze.cancha.domain.Incidencia>() {
            public com.raze.cancha.domain.Incidencia convert(java.lang.Long id) {
                return Incidencia.findIncidencia(id);
            }
        };
    }

	public Converter<String, Incidencia> getStringToIncidenciaConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.raze.cancha.domain.Incidencia>() {
            public com.raze.cancha.domain.Incidencia convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Incidencia.class);
            }
        };
    }

	public Converter<Jugador, String> getJugadorToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.raze.cancha.domain.Jugador, java.lang.String>() {
            public String convert(Jugador jugador) {
                return new StringBuilder().append(jugador.getNombre()).append(' ').append(jugador.getApellidoPaterno()).append(' ').append(jugador.getApellidoMaterno()).append(' ').append(jugador.getPosicion()).toString();
            }
        };
    }

	public Converter<Long, Jugador> getIdToJugadorConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.raze.cancha.domain.Jugador>() {
            public com.raze.cancha.domain.Jugador convert(java.lang.Long id) {
                return Jugador.findJugador(id);
            }
        };
    }

	public Converter<String, Jugador> getStringToJugadorConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.raze.cancha.domain.Jugador>() {
            public com.raze.cancha.domain.Jugador convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Jugador.class);
            }
        };
    }

	public Converter<Pago, String> getPagoToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.raze.cancha.domain.Pago, java.lang.String>() {
            public String convert(Pago pago) {
                return new StringBuilder().append(pago.getMonto()).append(' ').append(pago.getFechaCreacion()).append(' ').append(pago.getFechaModificacion()).toString();
            }
        };
    }

	public Converter<Long, Pago> getIdToPagoConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.raze.cancha.domain.Pago>() {
            public com.raze.cancha.domain.Pago convert(java.lang.Long id) {
                return Pago.findPago(id);
            }
        };
    }

	public Converter<String, Pago> getStringToPagoConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.raze.cancha.domain.Pago>() {
            public com.raze.cancha.domain.Pago convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Pago.class);
            }
        };
    }

	public Converter<Partido, String> getPartidoToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.raze.cancha.domain.Partido, java.lang.String>() {
            public String convert(Partido partido) {
                return new StringBuilder().append(partido.getFechaJuego()).append(' ').append(partido.getFechaCreacion()).append(' ').append(partido.getFechaModificacion()).toString();
            }
        };
    }

	public Converter<Long, Partido> getIdToPartidoConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.raze.cancha.domain.Partido>() {
            public com.raze.cancha.domain.Partido convert(java.lang.Long id) {
                return Partido.findPartido(id);
            }
        };
    }

	public Converter<String, Partido> getStringToPartidoConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.raze.cancha.domain.Partido>() {
            public com.raze.cancha.domain.Partido convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Partido.class);
            }
        };
    }

	public Converter<Sucursal, String> getSucursalToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.raze.cancha.domain.Sucursal, java.lang.String>() {
            public String convert(Sucursal sucursal) {
                return new StringBuilder().append(sucursal.getNombre()).append(' ').append(sucursal.getDomicilio()).append(' ').append(sucursal.getTelefono()).append(' ').append(sucursal.getFechaCreacion()).toString();
            }
        };
    }

	public Converter<Long, Sucursal> getIdToSucursalConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.raze.cancha.domain.Sucursal>() {
            public com.raze.cancha.domain.Sucursal convert(java.lang.Long id) {
                return Sucursal.findSucursal(id);
            }
        };
    }

	public Converter<String, Sucursal> getStringToSucursalConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.raze.cancha.domain.Sucursal>() {
            public com.raze.cancha.domain.Sucursal convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Sucursal.class);
            }
        };
    }

	public Converter<Torneo, String> getTorneoToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.raze.cancha.domain.Torneo, java.lang.String>() {
            public String convert(Torneo torneo) {
                return new StringBuilder().append(torneo.getNombre()).append(' ').append(torneo.getDescripcion()).append(' ').append(torneo.getFechaInicio()).append(' ').append(torneo.getFechaFin()).toString();
            }
        };
    }

	public Converter<Long, Torneo> getIdToTorneoConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.raze.cancha.domain.Torneo>() {
            public com.raze.cancha.domain.Torneo convert(java.lang.Long id) {
                return Torneo.findTorneo(id);
            }
        };
    }

	public Converter<String, Torneo> getStringToTorneoConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.raze.cancha.domain.Torneo>() {
            public com.raze.cancha.domain.Torneo convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Torneo.class);
            }
        };
    }

	public Converter<Usuario, String> getUsuarioToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.raze.cancha.domain.Usuario, java.lang.String>() {
            public String convert(Usuario usuario) {
                return new StringBuilder().append(usuario.getUsuario()).append(' ').append(usuario.getContrasena()).append(' ').append(usuario.getIntentos()).append(' ').append(usuario.getUltimaFechaAcceso()).toString();
            }
        };
    }

	public Converter<Long, Usuario> getIdToUsuarioConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.raze.cancha.domain.Usuario>() {
            public com.raze.cancha.domain.Usuario convert(java.lang.Long id) {
                return Usuario.findUsuario(id);
            }
        };
    }

	public Converter<String, Usuario> getStringToUsuarioConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.raze.cancha.domain.Usuario>() {
            public com.raze.cancha.domain.Usuario convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Usuario.class);
            }
        };
    }

	public Converter<Concepto, String> getConceptoToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.raze.cancha.reference.Concepto, java.lang.String>() {
            public String convert(Concepto concepto) {
                return new StringBuilder().append(concepto.getNombre()).append(' ').append(concepto.getDescripcion()).append(' ').append(concepto.getFechaCreacion()).append(' ').append(concepto.getFechaModificacion()).toString();
            }
        };
    }

	public Converter<Long, Concepto> getIdToConceptoConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.raze.cancha.reference.Concepto>() {
            public com.raze.cancha.reference.Concepto convert(java.lang.Long id) {
                return Concepto.findConcepto(id);
            }
        };
    }

	public Converter<String, Concepto> getStringToConceptoConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.raze.cancha.reference.Concepto>() {
            public com.raze.cancha.reference.Concepto convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Concepto.class);
            }
        };
    }

	public Converter<Modulo, String> getModuloToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.raze.cancha.reference.Modulo, java.lang.String>() {
            public String convert(Modulo modulo) {
                return new StringBuilder().append(modulo.getNombre()).append(' ').append(modulo.getDescripcion()).append(' ').append(modulo.getFechaCreacion()).append(' ').append(modulo.getFechaModificacion()).toString();
            }
        };
    }

	public Converter<Long, Modulo> getIdToModuloConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.raze.cancha.reference.Modulo>() {
            public com.raze.cancha.reference.Modulo convert(java.lang.Long id) {
                return Modulo.findModulo(id);
            }
        };
    }

	public Converter<String, Modulo> getStringToModuloConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.raze.cancha.reference.Modulo>() {
            public com.raze.cancha.reference.Modulo convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Modulo.class);
            }
        };
    }

	public Converter<Status, String> getStatusToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.raze.cancha.reference.Status, java.lang.String>() {
            public String convert(Status status) {
                return new StringBuilder().append(status.getNombre()).append(' ').append(status.getDescripcion()).append(' ').append(status.getFechaCreacion()).append(' ').append(status.getFechaModificacion()).toString();
            }
        };
    }

	public Converter<Long, Status> getIdToStatusConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.raze.cancha.reference.Status>() {
            public com.raze.cancha.reference.Status convert(java.lang.Long id) {
                return Status.findStatus(id);
            }
        };
    }

	public Converter<String, Status> getStringToStatusConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.raze.cancha.reference.Status>() {
            public com.raze.cancha.reference.Status convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Status.class);
            }
        };
    }

	public void installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getAlineacionToStringConverter());
        registry.addConverter(getIdToAlineacionConverter());
        registry.addConverter(getStringToAlineacionConverter());
        registry.addConverter(getArbitroToStringConverter());
        registry.addConverter(getIdToArbitroConverter());
        registry.addConverter(getStringToArbitroConverter());
        registry.addConverter(getCanchaToStringConverter());
        registry.addConverter(getIdToCanchaConverter());
        registry.addConverter(getStringToCanchaConverter());
        registry.addConverter(getDelegadoToStringConverter());
        registry.addConverter(getIdToDelegadoConverter());
        registry.addConverter(getStringToDelegadoConverter());
        registry.addConverter(getEmpresaToStringConverter());
        registry.addConverter(getIdToEmpresaConverter());
        registry.addConverter(getStringToEmpresaConverter());
        registry.addConverter(getEquipoToStringConverter());
        registry.addConverter(getIdToEquipoConverter());
        registry.addConverter(getStringToEquipoConverter());
        registry.addConverter(getEquipoTorneoToStringConverter());
        registry.addConverter(getIdToEquipoTorneoConverter());
        registry.addConverter(getStringToEquipoTorneoConverter());
        registry.addConverter(getEstadisticasPartidoToStringConverter());
        registry.addConverter(getIdToEstadisticasPartidoConverter());
        registry.addConverter(getStringToEstadisticasPartidoConverter());
        registry.addConverter(getIncidenciaToStringConverter());
        registry.addConverter(getIdToIncidenciaConverter());
        registry.addConverter(getStringToIncidenciaConverter());
        registry.addConverter(getJugadorToStringConverter());
        registry.addConverter(getIdToJugadorConverter());
        registry.addConverter(getStringToJugadorConverter());
        registry.addConverter(getPagoToStringConverter());
        registry.addConverter(getIdToPagoConverter());
        registry.addConverter(getStringToPagoConverter());
        registry.addConverter(getPartidoToStringConverter());
        registry.addConverter(getIdToPartidoConverter());
        registry.addConverter(getStringToPartidoConverter());
        registry.addConverter(getSucursalToStringConverter());
        registry.addConverter(getIdToSucursalConverter());
        registry.addConverter(getStringToSucursalConverter());
        registry.addConverter(getTorneoToStringConverter());
        registry.addConverter(getIdToTorneoConverter());
        registry.addConverter(getStringToTorneoConverter());
        registry.addConverter(getUsuarioToStringConverter());
        registry.addConverter(getIdToUsuarioConverter());
        registry.addConverter(getStringToUsuarioConverter());
        registry.addConverter(getConceptoToStringConverter());
        registry.addConverter(getIdToConceptoConverter());
        registry.addConverter(getStringToConceptoConverter());
        registry.addConverter(getModuloToStringConverter());
        registry.addConverter(getIdToModuloConverter());
        registry.addConverter(getStringToModuloConverter());
        registry.addConverter(getStatusToStringConverter());
        registry.addConverter(getIdToStatusConverter());
        registry.addConverter(getStringToStatusConverter());
    }

	public void afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
}
