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
@RooJpaActiveRecord(finders = { "findStatusesByIdModulo" })
@RooJson
public class Status {

    /**
     */
    @ManyToOne
    private Modulo idModulo;

    /**
     */
    private String nombre;

    /**
     */
    private String descripcion;

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

	public static Status fromJsonToStatus(String json) {
        return new JSONDeserializer<Status>()
        .use(null, Status.class).deserialize(json);
    }

	public static String toJsonArray(Collection<Status> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }

	public static String toJsonArray(Collection<Status> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }

	public static Collection<Status> fromJsonArrayToStatuses(String json) {
        return new JSONDeserializer<List<Status>>()
        .use("values", Status.class).deserialize(json);
    }

	public Modulo getIdModulo() {
        return this.idModulo;
    }

	public void setIdModulo(Modulo idModulo) {
        this.idModulo = idModulo;
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

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("idModulo", "nombre", "descripcion", "fechaCreacion", "fechaModificacion");

	public static final EntityManager entityManager() {
        EntityManager em = new Status().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countStatuses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Status o", Long.class).getSingleResult();
    }

	public static List<Status> findAllStatuses() {
        return entityManager().createQuery("SELECT o FROM Status o", Status.class).getResultList();
    }

	public static List<Status> findAllStatuses(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Status o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Status.class).getResultList();
    }

	public static Status findStatus(Long id) {
        if (id == null) return null;
        return entityManager().find(Status.class, id);
    }

	public static List<Status> findStatusEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Status o", Status.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<Status> findStatusEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Status o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Status.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Status attached = Status.findStatus(this.id);
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
    public Status merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Status merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public static Long countFindStatusesByIdModulo(Modulo idModulo) {
        if (idModulo == null) throw new IllegalArgumentException("The idModulo argument is required");
        EntityManager em = Status.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Status AS o WHERE o.idModulo = :idModulo", Long.class);
        q.setParameter("idModulo", idModulo);
        return ((Long) q.getSingleResult());
    }

	public static TypedQuery<Status> findStatusesByIdModulo(Modulo idModulo) {
        if (idModulo == null) throw new IllegalArgumentException("The idModulo argument is required");
        EntityManager em = Status.entityManager();
        TypedQuery<Status> q = em.createQuery("SELECT o FROM Status AS o WHERE o.idModulo = :idModulo", Status.class);
        q.setParameter("idModulo", idModulo);
        return q;
    }

	public static TypedQuery<Status> findStatusesByIdModulo(Modulo idModulo, String sortFieldName, String sortOrder) {
        if (idModulo == null) throw new IllegalArgumentException("The idModulo argument is required");
        EntityManager em = Status.entityManager();
        String jpaQuery = "SELECT o FROM Status AS o WHERE o.idModulo = :idModulo";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Status> q = em.createQuery(jpaQuery, Status.class);
        q.setParameter("idModulo", idModulo);
        return q;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
