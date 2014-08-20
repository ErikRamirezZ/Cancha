package com.raze.cancha.domain;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.ManyToOne;
import com.raze.cancha.reference.Status;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.Version;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@Entity
@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findDelegadoesByStatus", "findDelegadoesByCorreoELike", "findDelegadoesByCorreoELikeAndStatus", "findDelegadoesByIdEquipo", "findDelegadoesByIdEquipoAndStatus", "findDelegadoesByNombreLikeAndApellidoPaternoLike", "findDelegadoesByNombreLikeAndApellidoPaternoLikeAndStatus" })
@RooJson
public class Delegado {

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
    private String domicilio;

    /**
     */
    private String telefono;

    /**
     */
    private String celular;

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

	public String toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }

	public String toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }

	public static Delegado fromJsonToDelegado(String json) {
        return new JSONDeserializer<Delegado>()
        .use(null, Delegado.class).deserialize(json);
    }

	public static String toJsonArray(Collection<Delegado> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }

	public static String toJsonArray(Collection<Delegado> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }

	public static Collection<Delegado> fromJsonArrayToDelegadoes(String json) {
        return new JSONDeserializer<List<Delegado>>()
        .use("values", Delegado.class).deserialize(json);
    }

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

	public String getCelular() {
        return this.celular;
    }

	public void setCelular(String celular) {
        this.celular = celular;
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

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("idEquipo", "nombre", "apellidoPaterno", "apellidoMaterno", "domicilio", "telefono", "celular", "correoE", "status", "fechaCreacion", "fechaModificacion");

	public static final EntityManager entityManager() {
        EntityManager em = new Delegado().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countDelegadoes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Delegado o", Long.class).getSingleResult();
    }

	public static List<Delegado> findAllDelegadoes() {
        return entityManager().createQuery("SELECT o FROM Delegado o", Delegado.class).getResultList();
    }

	public static List<Delegado> findAllDelegadoes(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Delegado o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Delegado.class).getResultList();
    }

	public static Delegado findDelegado(Long id) {
        if (id == null) return null;
        return entityManager().find(Delegado.class, id);
    }

	public static List<Delegado> findDelegadoEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Delegado o", Delegado.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<Delegado> findDelegadoEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Delegado o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Delegado.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Delegado attached = Delegado.findDelegado(this.id);
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
    public Delegado merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Delegado merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	public static Long countFindDelegadoesByCorreoELike(String correoE) {
        if (correoE == null || correoE.length() == 0) throw new IllegalArgumentException("The correoE argument is required");
        correoE = correoE.replace('*', '%');
        if (correoE.charAt(0) != '%') {
            correoE = "%" + correoE;
        }
        if (correoE.charAt(correoE.length() - 1) != '%') {
            correoE = correoE + "%";
        }
        EntityManager em = Delegado.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Delegado AS o WHERE LOWER(o.correoE) LIKE LOWER(:correoE)", Long.class);
        q.setParameter("correoE", correoE);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindDelegadoesByCorreoELikeAndStatus(String correoE, Status status) {
        if (correoE == null || correoE.length() == 0) throw new IllegalArgumentException("The correoE argument is required");
        correoE = correoE.replace('*', '%');
        if (correoE.charAt(0) != '%') {
            correoE = "%" + correoE;
        }
        if (correoE.charAt(correoE.length() - 1) != '%') {
            correoE = correoE + "%";
        }
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Delegado.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Delegado AS o WHERE LOWER(o.correoE) LIKE LOWER(:correoE)  AND o.status = :status", Long.class);
        q.setParameter("correoE", correoE);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindDelegadoesByIdEquipo(Equipo idEquipo) {
        if (idEquipo == null) throw new IllegalArgumentException("The idEquipo argument is required");
        EntityManager em = Delegado.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Delegado AS o WHERE o.idEquipo = :idEquipo", Long.class);
        q.setParameter("idEquipo", idEquipo);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindDelegadoesByIdEquipoAndStatus(Equipo idEquipo, Status status) {
        if (idEquipo == null) throw new IllegalArgumentException("The idEquipo argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Delegado.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Delegado AS o WHERE o.idEquipo = :idEquipo AND o.status = :status", Long.class);
        q.setParameter("idEquipo", idEquipo);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindDelegadoesByNombreLikeAndApellidoPaternoLike(String nombre, String apellidoPaterno) {
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
        EntityManager em = Delegado.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Delegado AS o WHERE LOWER(o.nombre) LIKE LOWER(:nombre)  AND LOWER(o.apellidoPaterno) LIKE LOWER(:apellidoPaterno)", Long.class);
        q.setParameter("nombre", nombre);
        q.setParameter("apellidoPaterno", apellidoPaterno);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindDelegadoesByNombreLikeAndApellidoPaternoLikeAndStatus(String nombre, String apellidoPaterno, Status status) {
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
        EntityManager em = Delegado.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Delegado AS o WHERE LOWER(o.nombre) LIKE LOWER(:nombre)  AND LOWER(o.apellidoPaterno) LIKE LOWER(:apellidoPaterno)  AND o.status = :status", Long.class);
        q.setParameter("nombre", nombre);
        q.setParameter("apellidoPaterno", apellidoPaterno);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindDelegadoesByStatus(Status status) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Delegado.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Delegado AS o WHERE o.status = :status", Long.class);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static TypedQuery<Delegado> findDelegadoesByCorreoELike(String correoE) {
        if (correoE == null || correoE.length() == 0) throw new IllegalArgumentException("The correoE argument is required");
        correoE = correoE.replace('*', '%');
        if (correoE.charAt(0) != '%') {
            correoE = "%" + correoE;
        }
        if (correoE.charAt(correoE.length() - 1) != '%') {
            correoE = correoE + "%";
        }
        EntityManager em = Delegado.entityManager();
        TypedQuery<Delegado> q = em.createQuery("SELECT o FROM Delegado AS o WHERE LOWER(o.correoE) LIKE LOWER(:correoE)", Delegado.class);
        q.setParameter("correoE", correoE);
        return q;
    }

	public static TypedQuery<Delegado> findDelegadoesByCorreoELike(String correoE, String sortFieldName, String sortOrder) {
        if (correoE == null || correoE.length() == 0) throw new IllegalArgumentException("The correoE argument is required");
        correoE = correoE.replace('*', '%');
        if (correoE.charAt(0) != '%') {
            correoE = "%" + correoE;
        }
        if (correoE.charAt(correoE.length() - 1) != '%') {
            correoE = correoE + "%";
        }
        EntityManager em = Delegado.entityManager();
        String jpaQuery = "SELECT o FROM Delegado AS o WHERE LOWER(o.correoE) LIKE LOWER(:correoE)";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Delegado> q = em.createQuery(jpaQuery, Delegado.class);
        q.setParameter("correoE", correoE);
        return q;
    }

	public static TypedQuery<Delegado> findDelegadoesByCorreoELikeAndStatus(String correoE, Status status) {
        if (correoE == null || correoE.length() == 0) throw new IllegalArgumentException("The correoE argument is required");
        correoE = correoE.replace('*', '%');
        if (correoE.charAt(0) != '%') {
            correoE = "%" + correoE;
        }
        if (correoE.charAt(correoE.length() - 1) != '%') {
            correoE = correoE + "%";
        }
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Delegado.entityManager();
        TypedQuery<Delegado> q = em.createQuery("SELECT o FROM Delegado AS o WHERE LOWER(o.correoE) LIKE LOWER(:correoE)  AND o.status = :status", Delegado.class);
        q.setParameter("correoE", correoE);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Delegado> findDelegadoesByCorreoELikeAndStatus(String correoE, Status status, String sortFieldName, String sortOrder) {
        if (correoE == null || correoE.length() == 0) throw new IllegalArgumentException("The correoE argument is required");
        correoE = correoE.replace('*', '%');
        if (correoE.charAt(0) != '%') {
            correoE = "%" + correoE;
        }
        if (correoE.charAt(correoE.length() - 1) != '%') {
            correoE = correoE + "%";
        }
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Delegado.entityManager();
        String jpaQuery = "SELECT o FROM Delegado AS o WHERE LOWER(o.correoE) LIKE LOWER(:correoE)  AND o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Delegado> q = em.createQuery(jpaQuery, Delegado.class);
        q.setParameter("correoE", correoE);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Delegado> findDelegadoesByIdEquipo(Equipo idEquipo) {
        if (idEquipo == null) throw new IllegalArgumentException("The idEquipo argument is required");
        EntityManager em = Delegado.entityManager();
        TypedQuery<Delegado> q = em.createQuery("SELECT o FROM Delegado AS o WHERE o.idEquipo = :idEquipo", Delegado.class);
        q.setParameter("idEquipo", idEquipo);
        return q;
    }

	public static TypedQuery<Delegado> findDelegadoesByIdEquipo(Equipo idEquipo, String sortFieldName, String sortOrder) {
        if (idEquipo == null) throw new IllegalArgumentException("The idEquipo argument is required");
        EntityManager em = Delegado.entityManager();
        String jpaQuery = "SELECT o FROM Delegado AS o WHERE o.idEquipo = :idEquipo";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Delegado> q = em.createQuery(jpaQuery, Delegado.class);
        q.setParameter("idEquipo", idEquipo);
        return q;
    }

	public static TypedQuery<Delegado> findDelegadoesByIdEquipoAndStatus(Equipo idEquipo, Status status) {
        if (idEquipo == null) throw new IllegalArgumentException("The idEquipo argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Delegado.entityManager();
        TypedQuery<Delegado> q = em.createQuery("SELECT o FROM Delegado AS o WHERE o.idEquipo = :idEquipo AND o.status = :status", Delegado.class);
        q.setParameter("idEquipo", idEquipo);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Delegado> findDelegadoesByIdEquipoAndStatus(Equipo idEquipo, Status status, String sortFieldName, String sortOrder) {
        if (idEquipo == null) throw new IllegalArgumentException("The idEquipo argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Delegado.entityManager();
        String jpaQuery = "SELECT o FROM Delegado AS o WHERE o.idEquipo = :idEquipo AND o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Delegado> q = em.createQuery(jpaQuery, Delegado.class);
        q.setParameter("idEquipo", idEquipo);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Delegado> findDelegadoesByNombreLikeAndApellidoPaternoLike(String nombre, String apellidoPaterno) {
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
        EntityManager em = Delegado.entityManager();
        TypedQuery<Delegado> q = em.createQuery("SELECT o FROM Delegado AS o WHERE LOWER(o.nombre) LIKE LOWER(:nombre)  AND LOWER(o.apellidoPaterno) LIKE LOWER(:apellidoPaterno)", Delegado.class);
        q.setParameter("nombre", nombre);
        q.setParameter("apellidoPaterno", apellidoPaterno);
        return q;
    }

	public static TypedQuery<Delegado> findDelegadoesByNombreLikeAndApellidoPaternoLike(String nombre, String apellidoPaterno, String sortFieldName, String sortOrder) {
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
        EntityManager em = Delegado.entityManager();
        String jpaQuery = "SELECT o FROM Delegado AS o WHERE LOWER(o.nombre) LIKE LOWER(:nombre)  AND LOWER(o.apellidoPaterno) LIKE LOWER(:apellidoPaterno)";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Delegado> q = em.createQuery(jpaQuery, Delegado.class);
        q.setParameter("nombre", nombre);
        q.setParameter("apellidoPaterno", apellidoPaterno);
        return q;
    }

	public static TypedQuery<Delegado> findDelegadoesByNombreLikeAndApellidoPaternoLikeAndStatus(String nombre, String apellidoPaterno, Status status) {
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
        EntityManager em = Delegado.entityManager();
        TypedQuery<Delegado> q = em.createQuery("SELECT o FROM Delegado AS o WHERE LOWER(o.nombre) LIKE LOWER(:nombre)  AND LOWER(o.apellidoPaterno) LIKE LOWER(:apellidoPaterno)  AND o.status = :status", Delegado.class);
        q.setParameter("nombre", nombre);
        q.setParameter("apellidoPaterno", apellidoPaterno);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Delegado> findDelegadoesByNombreLikeAndApellidoPaternoLikeAndStatus(String nombre, String apellidoPaterno, Status status, String sortFieldName, String sortOrder) {
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
        EntityManager em = Delegado.entityManager();
        String jpaQuery = "SELECT o FROM Delegado AS o WHERE LOWER(o.nombre) LIKE LOWER(:nombre)  AND LOWER(o.apellidoPaterno) LIKE LOWER(:apellidoPaterno)  AND o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Delegado> q = em.createQuery(jpaQuery, Delegado.class);
        q.setParameter("nombre", nombre);
        q.setParameter("apellidoPaterno", apellidoPaterno);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Delegado> findDelegadoesByStatus(Status status) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Delegado.entityManager();
        TypedQuery<Delegado> q = em.createQuery("SELECT o FROM Delegado AS o WHERE o.status = :status", Delegado.class);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Delegado> findDelegadoesByStatus(Status status, String sortFieldName, String sortOrder) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Delegado.entityManager();
        String jpaQuery = "SELECT o FROM Delegado AS o WHERE o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Delegado> q = em.createQuery(jpaQuery, Delegado.class);
        q.setParameter("status", status);
        return q;
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
}
