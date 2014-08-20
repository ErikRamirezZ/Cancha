package com.raze.cancha.reference;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
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
@RooJpaActiveRecord(finders = { "findConceptoesByNombreLike", "findConceptoesByNombreLikeAndStatus", "findConceptoesByStatus" })
@RooJson
public class Concepto {

    /**
     */
    private String nombre;

    /**
     */
    private String descripcion;

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

	public static Long countFindConceptoesByNombreLike(String nombre) {
        if (nombre == null || nombre.length() == 0) throw new IllegalArgumentException("The nombre argument is required");
        nombre = nombre.replace('*', '%');
        if (nombre.charAt(0) != '%') {
            nombre = "%" + nombre;
        }
        if (nombre.charAt(nombre.length() - 1) != '%') {
            nombre = nombre + "%";
        }
        EntityManager em = Concepto.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Concepto AS o WHERE LOWER(o.nombre) LIKE LOWER(:nombre)", Long.class);
        q.setParameter("nombre", nombre);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindConceptoesByNombreLikeAndStatus(String nombre, Status status) {
        if (nombre == null || nombre.length() == 0) throw new IllegalArgumentException("The nombre argument is required");
        nombre = nombre.replace('*', '%');
        if (nombre.charAt(0) != '%') {
            nombre = "%" + nombre;
        }
        if (nombre.charAt(nombre.length() - 1) != '%') {
            nombre = nombre + "%";
        }
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Concepto.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Concepto AS o WHERE LOWER(o.nombre) LIKE LOWER(:nombre)  AND o.status = :status", Long.class);
        q.setParameter("nombre", nombre);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindConceptoesByStatus(Status status) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Concepto.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Concepto AS o WHERE o.status = :status", Long.class);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static TypedQuery<Concepto> findConceptoesByNombreLike(String nombre) {
        if (nombre == null || nombre.length() == 0) throw new IllegalArgumentException("The nombre argument is required");
        nombre = nombre.replace('*', '%');
        if (nombre.charAt(0) != '%') {
            nombre = "%" + nombre;
        }
        if (nombre.charAt(nombre.length() - 1) != '%') {
            nombre = nombre + "%";
        }
        EntityManager em = Concepto.entityManager();
        TypedQuery<Concepto> q = em.createQuery("SELECT o FROM Concepto AS o WHERE LOWER(o.nombre) LIKE LOWER(:nombre)", Concepto.class);
        q.setParameter("nombre", nombre);
        return q;
    }

	public static TypedQuery<Concepto> findConceptoesByNombreLike(String nombre, String sortFieldName, String sortOrder) {
        if (nombre == null || nombre.length() == 0) throw new IllegalArgumentException("The nombre argument is required");
        nombre = nombre.replace('*', '%');
        if (nombre.charAt(0) != '%') {
            nombre = "%" + nombre;
        }
        if (nombre.charAt(nombre.length() - 1) != '%') {
            nombre = nombre + "%";
        }
        EntityManager em = Concepto.entityManager();
        String jpaQuery = "SELECT o FROM Concepto AS o WHERE LOWER(o.nombre) LIKE LOWER(:nombre)";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Concepto> q = em.createQuery(jpaQuery, Concepto.class);
        q.setParameter("nombre", nombre);
        return q;
    }

	public static TypedQuery<Concepto> findConceptoesByNombreLikeAndStatus(String nombre, Status status) {
        if (nombre == null || nombre.length() == 0) throw new IllegalArgumentException("The nombre argument is required");
        nombre = nombre.replace('*', '%');
        if (nombre.charAt(0) != '%') {
            nombre = "%" + nombre;
        }
        if (nombre.charAt(nombre.length() - 1) != '%') {
            nombre = nombre + "%";
        }
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Concepto.entityManager();
        TypedQuery<Concepto> q = em.createQuery("SELECT o FROM Concepto AS o WHERE LOWER(o.nombre) LIKE LOWER(:nombre)  AND o.status = :status", Concepto.class);
        q.setParameter("nombre", nombre);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Concepto> findConceptoesByNombreLikeAndStatus(String nombre, Status status, String sortFieldName, String sortOrder) {
        if (nombre == null || nombre.length() == 0) throw new IllegalArgumentException("The nombre argument is required");
        nombre = nombre.replace('*', '%');
        if (nombre.charAt(0) != '%') {
            nombre = "%" + nombre;
        }
        if (nombre.charAt(nombre.length() - 1) != '%') {
            nombre = nombre + "%";
        }
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Concepto.entityManager();
        String jpaQuery = "SELECT o FROM Concepto AS o WHERE LOWER(o.nombre) LIKE LOWER(:nombre)  AND o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Concepto> q = em.createQuery(jpaQuery, Concepto.class);
        q.setParameter("nombre", nombre);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Concepto> findConceptoesByStatus(Status status) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Concepto.entityManager();
        TypedQuery<Concepto> q = em.createQuery("SELECT o FROM Concepto AS o WHERE o.status = :status", Concepto.class);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Concepto> findConceptoesByStatus(Status status, String sortFieldName, String sortOrder) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Concepto.entityManager();
        String jpaQuery = "SELECT o FROM Concepto AS o WHERE o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Concepto> q = em.createQuery(jpaQuery, Concepto.class);
        q.setParameter("status", status);
        return q;
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

	public String getDescripcion() {
        return this.descripcion;
    }

	public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("nombre", "descripcion", "status", "fechaCreacion", "fechaModificacion");

	public static final EntityManager entityManager() {
        EntityManager em = new Concepto().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countConceptoes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Concepto o", Long.class).getSingleResult();
    }

	public static List<Concepto> findAllConceptoes() {
        return entityManager().createQuery("SELECT o FROM Concepto o", Concepto.class).getResultList();
    }

	public static List<Concepto> findAllConceptoes(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Concepto o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Concepto.class).getResultList();
    }

	public static Concepto findConcepto(Long id) {
        if (id == null) return null;
        return entityManager().find(Concepto.class, id);
    }

	public static List<Concepto> findConceptoEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Concepto o", Concepto.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<Concepto> findConceptoEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Concepto o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Concepto.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Concepto attached = Concepto.findConcepto(this.id);
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
    public Concepto merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Concepto merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public String toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }

	public String toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }

	public static Concepto fromJsonToConcepto(String json) {
        return new JSONDeserializer<Concepto>()
        .use(null, Concepto.class).deserialize(json);
    }

	public static String toJsonArray(Collection<Concepto> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }

	public static String toJsonArray(Collection<Concepto> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }

	public static Collection<Concepto> fromJsonArrayToConceptoes(String json) {
        return new JSONDeserializer<List<Concepto>>()
        .use("values", Concepto.class).deserialize(json);
    }
}
