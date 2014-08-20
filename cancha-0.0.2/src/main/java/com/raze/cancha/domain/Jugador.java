package com.raze.cancha.domain;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Lob;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.Version;
import org.springframework.roo.classpath.operations.jsr303.RooUploadedFile;
import com.raze.cancha.reference.Status;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@Entity
@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findJugadorsByStatus", "findJugadorsByCorreoELike", "findJugadorsByCorreoELikeAndStatus", "findJugadorsByIdEquipo", "findJugadorsByIdEquipoAndStatus", "findJugadorsByNombreLikeAndApellidoPaternoLike", "findJugadorsByNombreLikeAndApellidoPaternoLikeAndStatus" })
@RooJson
public class Jugador {

    /**
     */
    @ManyToOne
    private Equipo idEquipo;

    /**
     */
    private String nombre;

    /**
     */
    private String apellidoPaterno;

    /**
     */
    private String apellidoMaterno;

    /**
     */
    private String posicion;

    /**
     */
    private int edad;

    /**
     */
    @RooUploadedFile(contentType = "image/jpeg", autoUpload = true)
    @Lob
    private byte[] foto;

    /**
     */
    private String domicilio;

    /**
     */
    private String telefono;

    /**
     */
    private String correoE;

    /**
     */
    @ManyToOne
    private Status status;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date fechaCreacion;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date fechaModificacion;

	public Equipo getIdEquipo() {
        return this.idEquipo;
    }

	public void setIdEquipo(Equipo idEquipo) {
        this.idEquipo = idEquipo;
    }

	public String getNombre() {
        return this.nombre;
    }

	public void setNombre(String nombre) {
        this.nombre = nombre;
    }

	public String getApellidoPaterno() {
        return this.apellidoPaterno;
    }

	public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

	public String getApellidoMaterno() {
        return this.apellidoMaterno;
    }

	public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

	public String getPosicion() {
        return this.posicion;
    }

	public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

	public int getEdad() {
        return this.edad;
    }

	public void setEdad(int edad) {
        this.edad = edad;
    }

	public byte[] getFoto() {
        return this.foto;
    }

	public void setFoto(byte[] foto) {
        this.foto = foto;
    }

	public String getDomicilio() {
        return this.domicilio;
    }

	public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

	public String getTelefono() {
        return this.telefono;
    }

	public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

	public String getCorreoE() {
        return this.correoE;
    }

	public void setCorreoE(String correoE) {
        this.correoE = correoE;
    }

	public Status getStatus() {
        return this.status;
    }

	public void setStatus(Status status) {
        this.status = status;
    }

	public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

	public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

	public Date getFechaModificacion() {
        return this.fechaModificacion;
    }

	public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

	@Version
    @Column(name = "version")
    private Integer version;

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public Integer getVersion() {
        return this.version;
    }

	public void setVersion(Integer version) {
        this.version = version;
    }

	public static Long countFindJugadorsByCorreoELike(String correoE) {
        if (correoE == null || correoE.length() == 0) throw new IllegalArgumentException("The correoE argument is required");
        correoE = correoE.replace('*', '%');
        if (correoE.charAt(0) != '%') {
            correoE = "%" + correoE;
        }
        if (correoE.charAt(correoE.length() - 1) != '%') {
            correoE = correoE + "%";
        }
        EntityManager em = Jugador.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Jugador AS o WHERE LOWER(o.correoE) LIKE LOWER(:correoE)", Long.class);
        q.setParameter("correoE", correoE);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindJugadorsByCorreoELikeAndStatus(String correoE, Status status) {
        if (correoE == null || correoE.length() == 0) throw new IllegalArgumentException("The correoE argument is required");
        correoE = correoE.replace('*', '%');
        if (correoE.charAt(0) != '%') {
            correoE = "%" + correoE;
        }
        if (correoE.charAt(correoE.length() - 1) != '%') {
            correoE = correoE + "%";
        }
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Jugador.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Jugador AS o WHERE LOWER(o.correoE) LIKE LOWER(:correoE)  AND o.status = :status", Long.class);
        q.setParameter("correoE", correoE);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindJugadorsByIdEquipo(Equipo idEquipo) {
        if (idEquipo == null) throw new IllegalArgumentException("The idEquipo argument is required");
        EntityManager em = Jugador.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Jugador AS o WHERE o.idEquipo = :idEquipo", Long.class);
        q.setParameter("idEquipo", idEquipo);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindJugadorsByIdEquipoAndStatus(Equipo idEquipo, Status status) {
        if (idEquipo == null) throw new IllegalArgumentException("The idEquipo argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Jugador.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Jugador AS o WHERE o.idEquipo = :idEquipo AND o.status = :status", Long.class);
        q.setParameter("idEquipo", idEquipo);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindJugadorsByNombreLikeAndApellidoPaternoLike(String nombre, String apellidoPaterno) {
        if (nombre == null || nombre.length() == 0) throw new IllegalArgumentException("The nombre argument is required");
        nombre = nombre.replace('*', '%');
        if (nombre.charAt(0) != '%') {
            nombre = "%" + nombre;
        }
        if (nombre.charAt(nombre.length() - 1) != '%') {
            nombre = nombre + "%";
        }
        if (apellidoPaterno == null || apellidoPaterno.length() == 0) throw new IllegalArgumentException("The apellidoPaterno argument is required");
        apellidoPaterno = apellidoPaterno.replace('*', '%');
        if (apellidoPaterno.charAt(0) != '%') {
            apellidoPaterno = "%" + apellidoPaterno;
        }
        if (apellidoPaterno.charAt(apellidoPaterno.length() - 1) != '%') {
            apellidoPaterno = apellidoPaterno + "%";
        }
        EntityManager em = Jugador.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Jugador AS o WHERE LOWER(o.nombre) LIKE LOWER(:nombre)  AND LOWER(o.apellidoPaterno) LIKE LOWER(:apellidoPaterno)", Long.class);
        q.setParameter("nombre", nombre);
        q.setParameter("apellidoPaterno", apellidoPaterno);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindJugadorsByNombreLikeAndApellidoPaternoLikeAndStatus(String nombre, String apellidoPaterno, Status status) {
        if (nombre == null || nombre.length() == 0) throw new IllegalArgumentException("The nombre argument is required");
        nombre = nombre.replace('*', '%');
        if (nombre.charAt(0) != '%') {
            nombre = "%" + nombre;
        }
        if (nombre.charAt(nombre.length() - 1) != '%') {
            nombre = nombre + "%";
        }
        if (apellidoPaterno == null || apellidoPaterno.length() == 0) throw new IllegalArgumentException("The apellidoPaterno argument is required");
        apellidoPaterno = apellidoPaterno.replace('*', '%');
        if (apellidoPaterno.charAt(0) != '%') {
            apellidoPaterno = "%" + apellidoPaterno;
        }
        if (apellidoPaterno.charAt(apellidoPaterno.length() - 1) != '%') {
            apellidoPaterno = apellidoPaterno + "%";
        }
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Jugador.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Jugador AS o WHERE LOWER(o.nombre) LIKE LOWER(:nombre)  AND LOWER(o.apellidoPaterno) LIKE LOWER(:apellidoPaterno)  AND o.status = :status", Long.class);
        q.setParameter("nombre", nombre);
        q.setParameter("apellidoPaterno", apellidoPaterno);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindJugadorsByStatus(Status status) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Jugador.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Jugador AS o WHERE o.status = :status", Long.class);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static TypedQuery<Jugador> findJugadorsByCorreoELike(String correoE) {
        if (correoE == null || correoE.length() == 0) throw new IllegalArgumentException("The correoE argument is required");
        correoE = correoE.replace('*', '%');
        if (correoE.charAt(0) != '%') {
            correoE = "%" + correoE;
        }
        if (correoE.charAt(correoE.length() - 1) != '%') {
            correoE = correoE + "%";
        }
        EntityManager em = Jugador.entityManager();
        TypedQuery<Jugador> q = em.createQuery("SELECT o FROM Jugador AS o WHERE LOWER(o.correoE) LIKE LOWER(:correoE)", Jugador.class);
        q.setParameter("correoE", correoE);
        return q;
    }

	public static TypedQuery<Jugador> findJugadorsByCorreoELike(String correoE, String sortFieldName, String sortOrder) {
        if (correoE == null || correoE.length() == 0) throw new IllegalArgumentException("The correoE argument is required");
        correoE = correoE.replace('*', '%');
        if (correoE.charAt(0) != '%') {
            correoE = "%" + correoE;
        }
        if (correoE.charAt(correoE.length() - 1) != '%') {
            correoE = correoE + "%";
        }
        EntityManager em = Jugador.entityManager();
        String jpaQuery = "SELECT o FROM Jugador AS o WHERE LOWER(o.correoE) LIKE LOWER(:correoE)";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Jugador> q = em.createQuery(jpaQuery, Jugador.class);
        q.setParameter("correoE", correoE);
        return q;
    }

	public static TypedQuery<Jugador> findJugadorsByCorreoELikeAndStatus(String correoE, Status status) {
        if (correoE == null || correoE.length() == 0) throw new IllegalArgumentException("The correoE argument is required");
        correoE = correoE.replace('*', '%');
        if (correoE.charAt(0) != '%') {
            correoE = "%" + correoE;
        }
        if (correoE.charAt(correoE.length() - 1) != '%') {
            correoE = correoE + "%";
        }
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Jugador.entityManager();
        TypedQuery<Jugador> q = em.createQuery("SELECT o FROM Jugador AS o WHERE LOWER(o.correoE) LIKE LOWER(:correoE)  AND o.status = :status", Jugador.class);
        q.setParameter("correoE", correoE);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Jugador> findJugadorsByCorreoELikeAndStatus(String correoE, Status status, String sortFieldName, String sortOrder) {
        if (correoE == null || correoE.length() == 0) throw new IllegalArgumentException("The correoE argument is required");
        correoE = correoE.replace('*', '%');
        if (correoE.charAt(0) != '%') {
            correoE = "%" + correoE;
        }
        if (correoE.charAt(correoE.length() - 1) != '%') {
            correoE = correoE + "%";
        }
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Jugador.entityManager();
        String jpaQuery = "SELECT o FROM Jugador AS o WHERE LOWER(o.correoE) LIKE LOWER(:correoE)  AND o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Jugador> q = em.createQuery(jpaQuery, Jugador.class);
        q.setParameter("correoE", correoE);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Jugador> findJugadorsByIdEquipo(Equipo idEquipo) {
        if (idEquipo == null) throw new IllegalArgumentException("The idEquipo argument is required");
        EntityManager em = Jugador.entityManager();
        TypedQuery<Jugador> q = em.createQuery("SELECT o FROM Jugador AS o WHERE o.idEquipo = :idEquipo", Jugador.class);
        q.setParameter("idEquipo", idEquipo);
        return q;
    }

	public static TypedQuery<Jugador> findJugadorsByIdEquipo(Equipo idEquipo, String sortFieldName, String sortOrder) {
        if (idEquipo == null) throw new IllegalArgumentException("The idEquipo argument is required");
        EntityManager em = Jugador.entityManager();
        String jpaQuery = "SELECT o FROM Jugador AS o WHERE o.idEquipo = :idEquipo";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Jugador> q = em.createQuery(jpaQuery, Jugador.class);
        q.setParameter("idEquipo", idEquipo);
        return q;
    }

	public static TypedQuery<Jugador> findJugadorsByIdEquipoAndStatus(Equipo idEquipo, Status status) {
        if (idEquipo == null) throw new IllegalArgumentException("The idEquipo argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Jugador.entityManager();
        TypedQuery<Jugador> q = em.createQuery("SELECT o FROM Jugador AS o WHERE o.idEquipo = :idEquipo AND o.status = :status", Jugador.class);
        q.setParameter("idEquipo", idEquipo);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Jugador> findJugadorsByIdEquipoAndStatus(Equipo idEquipo, Status status, String sortFieldName, String sortOrder) {
        if (idEquipo == null) throw new IllegalArgumentException("The idEquipo argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Jugador.entityManager();
        String jpaQuery = "SELECT o FROM Jugador AS o WHERE o.idEquipo = :idEquipo AND o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Jugador> q = em.createQuery(jpaQuery, Jugador.class);
        q.setParameter("idEquipo", idEquipo);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Jugador> findJugadorsByNombreLikeAndApellidoPaternoLike(String nombre, String apellidoPaterno) {
        if (nombre == null || nombre.length() == 0) throw new IllegalArgumentException("The nombre argument is required");
        nombre = nombre.replace('*', '%');
        if (nombre.charAt(0) != '%') {
            nombre = "%" + nombre;
        }
        if (nombre.charAt(nombre.length() - 1) != '%') {
            nombre = nombre + "%";
        }
        if (apellidoPaterno == null || apellidoPaterno.length() == 0) throw new IllegalArgumentException("The apellidoPaterno argument is required");
        apellidoPaterno = apellidoPaterno.replace('*', '%');
        if (apellidoPaterno.charAt(0) != '%') {
            apellidoPaterno = "%" + apellidoPaterno;
        }
        if (apellidoPaterno.charAt(apellidoPaterno.length() - 1) != '%') {
            apellidoPaterno = apellidoPaterno + "%";
        }
        EntityManager em = Jugador.entityManager();
        TypedQuery<Jugador> q = em.createQuery("SELECT o FROM Jugador AS o WHERE LOWER(o.nombre) LIKE LOWER(:nombre)  AND LOWER(o.apellidoPaterno) LIKE LOWER(:apellidoPaterno)", Jugador.class);
        q.setParameter("nombre", nombre);
        q.setParameter("apellidoPaterno", apellidoPaterno);
        return q;
    }

	public static TypedQuery<Jugador> findJugadorsByNombreLikeAndApellidoPaternoLike(String nombre, String apellidoPaterno, String sortFieldName, String sortOrder) {
        if (nombre == null || nombre.length() == 0) throw new IllegalArgumentException("The nombre argument is required");
        nombre = nombre.replace('*', '%');
        if (nombre.charAt(0) != '%') {
            nombre = "%" + nombre;
        }
        if (nombre.charAt(nombre.length() - 1) != '%') {
            nombre = nombre + "%";
        }
        if (apellidoPaterno == null || apellidoPaterno.length() == 0) throw new IllegalArgumentException("The apellidoPaterno argument is required");
        apellidoPaterno = apellidoPaterno.replace('*', '%');
        if (apellidoPaterno.charAt(0) != '%') {
            apellidoPaterno = "%" + apellidoPaterno;
        }
        if (apellidoPaterno.charAt(apellidoPaterno.length() - 1) != '%') {
            apellidoPaterno = apellidoPaterno + "%";
        }
        EntityManager em = Jugador.entityManager();
        String jpaQuery = "SELECT o FROM Jugador AS o WHERE LOWER(o.nombre) LIKE LOWER(:nombre)  AND LOWER(o.apellidoPaterno) LIKE LOWER(:apellidoPaterno)";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Jugador> q = em.createQuery(jpaQuery, Jugador.class);
        q.setParameter("nombre", nombre);
        q.setParameter("apellidoPaterno", apellidoPaterno);
        return q;
    }

	public static TypedQuery<Jugador> findJugadorsByNombreLikeAndApellidoPaternoLikeAndStatus(String nombre, String apellidoPaterno, Status status) {
        if (nombre == null || nombre.length() == 0) throw new IllegalArgumentException("The nombre argument is required");
        nombre = nombre.replace('*', '%');
        if (nombre.charAt(0) != '%') {
            nombre = "%" + nombre;
        }
        if (nombre.charAt(nombre.length() - 1) != '%') {
            nombre = nombre + "%";
        }
        if (apellidoPaterno == null || apellidoPaterno.length() == 0) throw new IllegalArgumentException("The apellidoPaterno argument is required");
        apellidoPaterno = apellidoPaterno.replace('*', '%');
        if (apellidoPaterno.charAt(0) != '%') {
            apellidoPaterno = "%" + apellidoPaterno;
        }
        if (apellidoPaterno.charAt(apellidoPaterno.length() - 1) != '%') {
            apellidoPaterno = apellidoPaterno + "%";
        }
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Jugador.entityManager();
        TypedQuery<Jugador> q = em.createQuery("SELECT o FROM Jugador AS o WHERE LOWER(o.nombre) LIKE LOWER(:nombre)  AND LOWER(o.apellidoPaterno) LIKE LOWER(:apellidoPaterno)  AND o.status = :status", Jugador.class);
        q.setParameter("nombre", nombre);
        q.setParameter("apellidoPaterno", apellidoPaterno);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Jugador> findJugadorsByNombreLikeAndApellidoPaternoLikeAndStatus(String nombre, String apellidoPaterno, Status status, String sortFieldName, String sortOrder) {
        if (nombre == null || nombre.length() == 0) throw new IllegalArgumentException("The nombre argument is required");
        nombre = nombre.replace('*', '%');
        if (nombre.charAt(0) != '%') {
            nombre = "%" + nombre;
        }
        if (nombre.charAt(nombre.length() - 1) != '%') {
            nombre = nombre + "%";
        }
        if (apellidoPaterno == null || apellidoPaterno.length() == 0) throw new IllegalArgumentException("The apellidoPaterno argument is required");
        apellidoPaterno = apellidoPaterno.replace('*', '%');
        if (apellidoPaterno.charAt(0) != '%') {
            apellidoPaterno = "%" + apellidoPaterno;
        }
        if (apellidoPaterno.charAt(apellidoPaterno.length() - 1) != '%') {
            apellidoPaterno = apellidoPaterno + "%";
        }
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Jugador.entityManager();
        String jpaQuery = "SELECT o FROM Jugador AS o WHERE LOWER(o.nombre) LIKE LOWER(:nombre)  AND LOWER(o.apellidoPaterno) LIKE LOWER(:apellidoPaterno)  AND o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Jugador> q = em.createQuery(jpaQuery, Jugador.class);
        q.setParameter("nombre", nombre);
        q.setParameter("apellidoPaterno", apellidoPaterno);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Jugador> findJugadorsByStatus(Status status) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Jugador.entityManager();
        TypedQuery<Jugador> q = em.createQuery("SELECT o FROM Jugador AS o WHERE o.status = :status", Jugador.class);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Jugador> findJugadorsByStatus(Status status, String sortFieldName, String sortOrder) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Jugador.entityManager();
        String jpaQuery = "SELECT o FROM Jugador AS o WHERE o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Jugador> q = em.createQuery(jpaQuery, Jugador.class);
        q.setParameter("status", status);
        return q;
    }

	public String toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }

	public String toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }

	public static Jugador fromJsonToJugador(String json) {
        return new JSONDeserializer<Jugador>()
        .use(null, Jugador.class).deserialize(json);
    }

	public static String toJsonArray(Collection<Jugador> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }

	public static String toJsonArray(Collection<Jugador> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }

	public static Collection<Jugador> fromJsonArrayToJugadors(String json) {
        return new JSONDeserializer<List<Jugador>>()
        .use("values", Jugador.class).deserialize(json);
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("idEquipo", "nombre", "apellidoPaterno", "apellidoMaterno", "posicion", "edad", "foto", "domicilio", "telefono", "correoE", "status", "fechaCreacion", "fechaModificacion");

	public static final EntityManager entityManager() {
        EntityManager em = new Jugador().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countJugadors() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Jugador o", Long.class).getSingleResult();
    }

	public static List<Jugador> findAllJugadors() {
        return entityManager().createQuery("SELECT o FROM Jugador o", Jugador.class).getResultList();
    }

	public static List<Jugador> findAllJugadors(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Jugador o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Jugador.class).getResultList();
    }

	public static Jugador findJugador(Long id) {
        if (id == null) return null;
        return entityManager().find(Jugador.class, id);
    }

	public static List<Jugador> findJugadorEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Jugador o", Jugador.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<Jugador> findJugadorEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Jugador o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Jugador.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	@Transactional
    public void persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }

	@Transactional
    public void remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Jugador attached = Jugador.findJugador(this.id);
            this.entityManager.remove(attached);
        }
    }

	@Transactional
    public void flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }

	@Transactional
    public void clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }

	@Transactional
    public Jugador merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Jugador merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
}
