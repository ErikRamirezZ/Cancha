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
@RooJpaActiveRecord(finders = { "findCanchasByStatus", "findCanchasByIdSucursalAndStatus" })
@RooJson
public class Cancha {

    /**
     */
    @ManyToOne
    private Sucursal idSucursal;

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

	public static Long countFindCanchasByIdSucursalAndStatus(Sucursal idSucursal, Status status) {
        if (idSucursal == null) throw new IllegalArgumentException("The idSucursal argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Cancha.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Cancha AS o WHERE o.idSucursal = :idSucursal AND o.status = :status", Long.class);
        q.setParameter("idSucursal", idSucursal);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindCanchasByStatus(Status status) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Cancha.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Cancha AS o WHERE o.status = :status", Long.class);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static TypedQuery<Cancha> findCanchasByIdSucursalAndStatus(Sucursal idSucursal, Status status) {
        if (idSucursal == null) throw new IllegalArgumentException("The idSucursal argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Cancha.entityManager();
        TypedQuery<Cancha> q = em.createQuery("SELECT o FROM Cancha AS o WHERE o.idSucursal = :idSucursal AND o.status = :status", Cancha.class);
        q.setParameter("idSucursal", idSucursal);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Cancha> findCanchasByIdSucursalAndStatus(Sucursal idSucursal, Status status, String sortFieldName, String sortOrder) {
        if (idSucursal == null) throw new IllegalArgumentException("The idSucursal argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Cancha.entityManager();
        String jpaQuery = "SELECT o FROM Cancha AS o WHERE o.idSucursal = :idSucursal AND o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Cancha> q = em.createQuery(jpaQuery, Cancha.class);
        q.setParameter("idSucursal", idSucursal);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Cancha> findCanchasByStatus(Status status) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Cancha.entityManager();
        TypedQuery<Cancha> q = em.createQuery("SELECT o FROM Cancha AS o WHERE o.status = :status", Cancha.class);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Cancha> findCanchasByStatus(Status status, String sortFieldName, String sortOrder) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Cancha.entityManager();
        String jpaQuery = "SELECT o FROM Cancha AS o WHERE o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Cancha> q = em.createQuery(jpaQuery, Cancha.class);
        q.setParameter("status", status);
        return q;
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("idSucursal", "nombre", "descripcion", "status", "fechaCreacion", "fechaModificacion");

	public static final EntityManager entityManager() {
        EntityManager em = new Cancha().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countCanchas() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Cancha o", Long.class).getSingleResult();
    }

	public static List<Cancha> findAllCanchas() {
        return entityManager().createQuery("SELECT o FROM Cancha o", Cancha.class).getResultList();
    }

	public static List<Cancha> findAllCanchas(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Cancha o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Cancha.class).getResultList();
    }

	public static Cancha findCancha(Long id) {
        if (id == null) return null;
        return entityManager().find(Cancha.class, id);
    }

	public static List<Cancha> findCanchaEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Cancha o", Cancha.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<Cancha> findCanchaEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Cancha o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Cancha.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Cancha attached = Cancha.findCancha(this.id);
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
    public Cancha merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Cancha merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	public Sucursal getIdSucursal() {
        return this.idSucursal;
    }

	public void setIdSucursal(Sucursal idSucursal) {
        this.idSucursal = idSucursal;
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

	public String toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }

	public String toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }

	public static Cancha fromJsonToCancha(String json) {
        return new JSONDeserializer<Cancha>()
        .use(null, Cancha.class).deserialize(json);
    }

	public static String toJsonArray(Collection<Cancha> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }

	public static String toJsonArray(Collection<Cancha> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }

	public static Collection<Cancha> fromJsonArrayToCanchas(String json) {
        return new JSONDeserializer<List<Cancha>>()
        .use("values", Cancha.class).deserialize(json);
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
