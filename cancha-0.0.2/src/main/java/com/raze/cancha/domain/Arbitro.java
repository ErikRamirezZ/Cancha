package com.raze.cancha.domain;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import com.raze.cancha.reference.Status;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import javax.persistence.ManyToOne;
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

@Entity
@Configurable
@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findArbitroesByStatus", "findArbitroesByCorreoEEqualsAndStatus", "findArbitroesByCorreoEEquals", "findArbitroesByCorreoELike", "findArbitroesByCorreoELikeAndStatus", "findArbitroesByNombreLike", "findArbitroesByNombreLikeAndStatus", "findArbitroesByNombreLikeAndApellidoPaternoLike", "findArbitroesByNombreLikeAndApellidoPaternoLikeAndStatus" })
@RooJson
public class Arbitro {

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
    private int edad;

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

	public static Arbitro fromJsonToArbitro(String json) {
        return new JSONDeserializer<Arbitro>()
        .use(null, Arbitro.class).deserialize(json);
    }

	public static String toJsonArray(Collection<Arbitro> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }

	public static String toJsonArray(Collection<Arbitro> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }

	public static Collection<Arbitro> fromJsonArrayToArbitroes(String json) {
        return new JSONDeserializer<List<Arbitro>>()
        .use("values", Arbitro.class).deserialize(json);
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
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

	public int getEdad() {
        return this.edad;
    }

	public void setEdad(int edad) {
        this.edad = edad;
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

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("nombre", "apellidoPaterno", "apellidoMaterno", "edad", "domicilio", "telefono", "celular", "correoE", "status", "fechaCreacion", "fechaModificacion");

	public static final EntityManager entityManager() {
        EntityManager em = new Arbitro().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countArbitroes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Arbitro o", Long.class).getSingleResult();
    }

	public static List<Arbitro> findAllArbitroes() {
        return entityManager().createQuery("SELECT o FROM Arbitro o", Arbitro.class).getResultList();
    }

	public static List<Arbitro> findAllArbitroes(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Arbitro o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Arbitro.class).getResultList();
    }

	public static Arbitro findArbitro(Long id) {
        if (id == null) return null;
        return entityManager().find(Arbitro.class, id);
    }

	public static List<Arbitro> findArbitroEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Arbitro o", Arbitro.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<Arbitro> findArbitroEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Arbitro o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Arbitro.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Arbitro attached = Arbitro.findArbitro(this.id);
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
    public Arbitro merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Arbitro merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public static Long countFindArbitroesByCorreoEEquals(String correoE) {
        if (correoE == null || correoE.length() == 0) throw new IllegalArgumentException("The correoE argument is required");
        EntityManager em = Arbitro.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Arbitro AS o WHERE o.correoE = :correoE", Long.class);
        q.setParameter("correoE", correoE);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindArbitroesByCorreoEEqualsAndStatus(String correoE, Status status) {
        if (correoE == null || correoE.length() == 0) throw new IllegalArgumentException("The correoE argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Arbitro.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Arbitro AS o WHERE o.correoE = :correoE  AND o.status = :status", Long.class);
        q.setParameter("correoE", correoE);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindArbitroesByCorreoELike(String correoE) {
        if (correoE == null || correoE.length() == 0) throw new IllegalArgumentException("The correoE argument is required");
        correoE = correoE.replace('*', '%');
        if (correoE.charAt(0) != '%') {
            correoE = "%" + correoE;
        }
        if (correoE.charAt(correoE.length() - 1) != '%') {
            correoE = correoE + "%";
        }
        EntityManager em = Arbitro.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Arbitro AS o WHERE LOWER(o.correoE) LIKE LOWER(:correoE)", Long.class);
        q.setParameter("correoE", correoE);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindArbitroesByCorreoELikeAndStatus(String correoE, Status status) {
        if (correoE == null || correoE.length() == 0) throw new IllegalArgumentException("The correoE argument is required");
        correoE = correoE.replace('*', '%');
        if (correoE.charAt(0) != '%') {
            correoE = "%" + correoE;
        }
        if (correoE.charAt(correoE.length() - 1) != '%') {
            correoE = correoE + "%";
        }
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Arbitro.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Arbitro AS o WHERE LOWER(o.correoE) LIKE LOWER(:correoE)  AND o.status = :status", Long.class);
        q.setParameter("correoE", correoE);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindArbitroesByNombreLike(String nombre) {
        if (nombre == null || nombre.length() == 0) throw new IllegalArgumentException("The nombre argument is required");
        nombre = nombre.replace('*', '%');
        if (nombre.charAt(0) != '%') {
            nombre = "%" + nombre;
        }
        if (nombre.charAt(nombre.length() - 1) != '%') {
            nombre = nombre + "%";
        }
        EntityManager em = Arbitro.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Arbitro AS o WHERE LOWER(o.nombre) LIKE LOWER(:nombre)", Long.class);
        q.setParameter("nombre", nombre);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindArbitroesByNombreLikeAndApellidoPaternoLike(String nombre, String apellidoPaterno) {
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
        EntityManager em = Arbitro.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Arbitro AS o WHERE LOWER(o.nombre) LIKE LOWER(:nombre)  AND LOWER(o.apellidoPaterno) LIKE LOWER(:apellidoPaterno)", Long.class);
        q.setParameter("nombre", nombre);
        q.setParameter("apellidoPaterno", apellidoPaterno);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindArbitroesByNombreLikeAndApellidoPaternoLikeAndStatus(String nombre, String apellidoPaterno, Status status) {
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
        EntityManager em = Arbitro.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Arbitro AS o WHERE LOWER(o.nombre) LIKE LOWER(:nombre)  AND LOWER(o.apellidoPaterno) LIKE LOWER(:apellidoPaterno)  AND o.status = :status", Long.class);
        q.setParameter("nombre", nombre);
        q.setParameter("apellidoPaterno", apellidoPaterno);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindArbitroesByNombreLikeAndStatus(String nombre, Status status) {
        if (nombre == null || nombre.length() == 0) throw new IllegalArgumentException("The nombre argument is required");
        nombre = nombre.replace('*', '%');
        if (nombre.charAt(0) != '%') {
            nombre = "%" + nombre;
        }
        if (nombre.charAt(nombre.length() - 1) != '%') {
            nombre = nombre + "%";
        }
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Arbitro.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Arbitro AS o WHERE LOWER(o.nombre) LIKE LOWER(:nombre)  AND o.status = :status", Long.class);
        q.setParameter("nombre", nombre);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindArbitroesByStatus(Status status) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Arbitro.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Arbitro AS o WHERE o.status = :status", Long.class);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static TypedQuery<Arbitro> findArbitroesByCorreoEEquals(String correoE) {
        if (correoE == null || correoE.length() == 0) throw new IllegalArgumentException("The correoE argument is required");
        EntityManager em = Arbitro.entityManager();
        TypedQuery<Arbitro> q = em.createQuery("SELECT o FROM Arbitro AS o WHERE o.correoE = :correoE", Arbitro.class);
        q.setParameter("correoE", correoE);
        return q;
    }

	public static TypedQuery<Arbitro> findArbitroesByCorreoEEquals(String correoE, String sortFieldName, String sortOrder) {
        if (correoE == null || correoE.length() == 0) throw new IllegalArgumentException("The correoE argument is required");
        EntityManager em = Arbitro.entityManager();
        String jpaQuery = "SELECT o FROM Arbitro AS o WHERE o.correoE = :correoE";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Arbitro> q = em.createQuery(jpaQuery, Arbitro.class);
        q.setParameter("correoE", correoE);
        return q;
    }

	public static TypedQuery<Arbitro> findArbitroesByCorreoEEqualsAndStatus(String correoE, Status status) {
        if (correoE == null || correoE.length() == 0) throw new IllegalArgumentException("The correoE argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Arbitro.entityManager();
        TypedQuery<Arbitro> q = em.createQuery("SELECT o FROM Arbitro AS o WHERE o.correoE = :correoE  AND o.status = :status", Arbitro.class);
        q.setParameter("correoE", correoE);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Arbitro> findArbitroesByCorreoEEqualsAndStatus(String correoE, Status status, String sortFieldName, String sortOrder) {
        if (correoE == null || correoE.length() == 0) throw new IllegalArgumentException("The correoE argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Arbitro.entityManager();
        String jpaQuery = "SELECT o FROM Arbitro AS o WHERE o.correoE = :correoE  AND o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Arbitro> q = em.createQuery(jpaQuery, Arbitro.class);
        q.setParameter("correoE", correoE);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Arbitro> findArbitroesByCorreoELike(String correoE) {
        if (correoE == null || correoE.length() == 0) throw new IllegalArgumentException("The correoE argument is required");
        correoE = correoE.replace('*', '%');
        if (correoE.charAt(0) != '%') {
            correoE = "%" + correoE;
        }
        if (correoE.charAt(correoE.length() - 1) != '%') {
            correoE = correoE + "%";
        }
        EntityManager em = Arbitro.entityManager();
        TypedQuery<Arbitro> q = em.createQuery("SELECT o FROM Arbitro AS o WHERE LOWER(o.correoE) LIKE LOWER(:correoE)", Arbitro.class);
        q.setParameter("correoE", correoE);
        return q;
    }

	public static TypedQuery<Arbitro> findArbitroesByCorreoELike(String correoE, String sortFieldName, String sortOrder) {
        if (correoE == null || correoE.length() == 0) throw new IllegalArgumentException("The correoE argument is required");
        correoE = correoE.replace('*', '%');
        if (correoE.charAt(0) != '%') {
            correoE = "%" + correoE;
        }
        if (correoE.charAt(correoE.length() - 1) != '%') {
            correoE = correoE + "%";
        }
        EntityManager em = Arbitro.entityManager();
        String jpaQuery = "SELECT o FROM Arbitro AS o WHERE LOWER(o.correoE) LIKE LOWER(:correoE)";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Arbitro> q = em.createQuery(jpaQuery, Arbitro.class);
        q.setParameter("correoE", correoE);
        return q;
    }

	public static TypedQuery<Arbitro> findArbitroesByCorreoELikeAndStatus(String correoE, Status status) {
        if (correoE == null || correoE.length() == 0) throw new IllegalArgumentException("The correoE argument is required");
        correoE = correoE.replace('*', '%');
        if (correoE.charAt(0) != '%') {
            correoE = "%" + correoE;
        }
        if (correoE.charAt(correoE.length() - 1) != '%') {
            correoE = correoE + "%";
        }
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Arbitro.entityManager();
        TypedQuery<Arbitro> q = em.createQuery("SELECT o FROM Arbitro AS o WHERE LOWER(o.correoE) LIKE LOWER(:correoE)  AND o.status = :status", Arbitro.class);
        q.setParameter("correoE", correoE);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Arbitro> findArbitroesByCorreoELikeAndStatus(String correoE, Status status, String sortFieldName, String sortOrder) {
        if (correoE == null || correoE.length() == 0) throw new IllegalArgumentException("The correoE argument is required");
        correoE = correoE.replace('*', '%');
        if (correoE.charAt(0) != '%') {
            correoE = "%" + correoE;
        }
        if (correoE.charAt(correoE.length() - 1) != '%') {
            correoE = correoE + "%";
        }
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Arbitro.entityManager();
        String jpaQuery = "SELECT o FROM Arbitro AS o WHERE LOWER(o.correoE) LIKE LOWER(:correoE)  AND o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Arbitro> q = em.createQuery(jpaQuery, Arbitro.class);
        q.setParameter("correoE", correoE);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Arbitro> findArbitroesByNombreLike(String nombre) {
        if (nombre == null || nombre.length() == 0) throw new IllegalArgumentException("The nombre argument is required");
        nombre = nombre.replace('*', '%');
        if (nombre.charAt(0) != '%') {
            nombre = "%" + nombre;
        }
        if (nombre.charAt(nombre.length() - 1) != '%') {
            nombre = nombre + "%";
        }
        EntityManager em = Arbitro.entityManager();
        TypedQuery<Arbitro> q = em.createQuery("SELECT o FROM Arbitro AS o WHERE LOWER(o.nombre) LIKE LOWER(:nombre)", Arbitro.class);
        q.setParameter("nombre", nombre);
        return q;
    }

	public static TypedQuery<Arbitro> findArbitroesByNombreLike(String nombre, String sortFieldName, String sortOrder) {
        if (nombre == null || nombre.length() == 0) throw new IllegalArgumentException("The nombre argument is required");
        nombre = nombre.replace('*', '%');
        if (nombre.charAt(0) != '%') {
            nombre = "%" + nombre;
        }
        if (nombre.charAt(nombre.length() - 1) != '%') {
            nombre = nombre + "%";
        }
        EntityManager em = Arbitro.entityManager();
        String jpaQuery = "SELECT o FROM Arbitro AS o WHERE LOWER(o.nombre) LIKE LOWER(:nombre)";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Arbitro> q = em.createQuery(jpaQuery, Arbitro.class);
        q.setParameter("nombre", nombre);
        return q;
    }

	public static TypedQuery<Arbitro> findArbitroesByNombreLikeAndApellidoPaternoLike(String nombre, String apellidoPaterno) {
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
        EntityManager em = Arbitro.entityManager();
        TypedQuery<Arbitro> q = em.createQuery("SELECT o FROM Arbitro AS o WHERE LOWER(o.nombre) LIKE LOWER(:nombre)  AND LOWER(o.apellidoPaterno) LIKE LOWER(:apellidoPaterno)", Arbitro.class);
        q.setParameter("nombre", nombre);
        q.setParameter("apellidoPaterno", apellidoPaterno);
        return q;
    }

	public static TypedQuery<Arbitro> findArbitroesByNombreLikeAndApellidoPaternoLike(String nombre, String apellidoPaterno, String sortFieldName, String sortOrder) {
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
        EntityManager em = Arbitro.entityManager();
        String jpaQuery = "SELECT o FROM Arbitro AS o WHERE LOWER(o.nombre) LIKE LOWER(:nombre)  AND LOWER(o.apellidoPaterno) LIKE LOWER(:apellidoPaterno)";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Arbitro> q = em.createQuery(jpaQuery, Arbitro.class);
        q.setParameter("nombre", nombre);
        q.setParameter("apellidoPaterno", apellidoPaterno);
        return q;
    }

	public static TypedQuery<Arbitro> findArbitroesByNombreLikeAndApellidoPaternoLikeAndStatus(String nombre, String apellidoPaterno, Status status) {
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
        EntityManager em = Arbitro.entityManager();
        TypedQuery<Arbitro> q = em.createQuery("SELECT o FROM Arbitro AS o WHERE LOWER(o.nombre) LIKE LOWER(:nombre)  AND LOWER(o.apellidoPaterno) LIKE LOWER(:apellidoPaterno)  AND o.status = :status", Arbitro.class);
        q.setParameter("nombre", nombre);
        q.setParameter("apellidoPaterno", apellidoPaterno);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Arbitro> findArbitroesByNombreLikeAndApellidoPaternoLikeAndStatus(String nombre, String apellidoPaterno, Status status, String sortFieldName, String sortOrder) {
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
        EntityManager em = Arbitro.entityManager();
        String jpaQuery = "SELECT o FROM Arbitro AS o WHERE LOWER(o.nombre) LIKE LOWER(:nombre)  AND LOWER(o.apellidoPaterno) LIKE LOWER(:apellidoPaterno)  AND o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Arbitro> q = em.createQuery(jpaQuery, Arbitro.class);
        q.setParameter("nombre", nombre);
        q.setParameter("apellidoPaterno", apellidoPaterno);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Arbitro> findArbitroesByNombreLikeAndStatus(String nombre, Status status) {
        if (nombre == null || nombre.length() == 0) throw new IllegalArgumentException("The nombre argument is required");
        nombre = nombre.replace('*', '%');
        if (nombre.charAt(0) != '%') {
            nombre = "%" + nombre;
        }
        if (nombre.charAt(nombre.length() - 1) != '%') {
            nombre = nombre + "%";
        }
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Arbitro.entityManager();
        TypedQuery<Arbitro> q = em.createQuery("SELECT o FROM Arbitro AS o WHERE LOWER(o.nombre) LIKE LOWER(:nombre)  AND o.status = :status", Arbitro.class);
        q.setParameter("nombre", nombre);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Arbitro> findArbitroesByNombreLikeAndStatus(String nombre, Status status, String sortFieldName, String sortOrder) {
        if (nombre == null || nombre.length() == 0) throw new IllegalArgumentException("The nombre argument is required");
        nombre = nombre.replace('*', '%');
        if (nombre.charAt(0) != '%') {
            nombre = "%" + nombre;
        }
        if (nombre.charAt(nombre.length() - 1) != '%') {
            nombre = nombre + "%";
        }
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Arbitro.entityManager();
        String jpaQuery = "SELECT o FROM Arbitro AS o WHERE LOWER(o.nombre) LIKE LOWER(:nombre)  AND o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Arbitro> q = em.createQuery(jpaQuery, Arbitro.class);
        q.setParameter("nombre", nombre);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Arbitro> findArbitroesByStatus(Status status) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Arbitro.entityManager();
        TypedQuery<Arbitro> q = em.createQuery("SELECT o FROM Arbitro AS o WHERE o.status = :status", Arbitro.class);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Arbitro> findArbitroesByStatus(Status status, String sortFieldName, String sortOrder) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Arbitro.entityManager();
        String jpaQuery = "SELECT o FROM Arbitro AS o WHERE o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Arbitro> q = em.createQuery(jpaQuery, Arbitro.class);
        q.setParameter("status", status);
        return q;
    }
}
