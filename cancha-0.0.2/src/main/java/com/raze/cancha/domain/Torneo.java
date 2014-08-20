package com.raze.cancha.domain;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
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
import com.raze.cancha.reference.Status;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@Entity
@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findTorneosByStatus", "findTorneosByIdSucursalAndStatus" })
@RooJson
public class Torneo {

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
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date fechaInicio;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date fechaFin;

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

	public static Torneo fromJsonToTorneo(String json) {
        return new JSONDeserializer<Torneo>()
        .use(null, Torneo.class).deserialize(json);
    }

	public static String toJsonArray(Collection<Torneo> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }

	public static String toJsonArray(Collection<Torneo> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }

	public static Collection<Torneo> fromJsonArrayToTorneos(String json) {
        return new JSONDeserializer<List<Torneo>>()
        .use("values", Torneo.class).deserialize(json);
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

	public Date getFechaInicio() {
        return this.fechaInicio;
    }

	public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

	public Date getFechaFin() {
        return this.fechaFin;
    }

	public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
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

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("idSucursal", "nombre", "descripcion", "fechaInicio", "fechaFin", "status", "fechaCreacion", "fechaModificacion");

	public static final EntityManager entityManager() {
        EntityManager em = new Torneo().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countTorneos() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Torneo o", Long.class).getSingleResult();
    }

	public static List<Torneo> findAllTorneos() {
        return entityManager().createQuery("SELECT o FROM Torneo o", Torneo.class).getResultList();
    }

	public static List<Torneo> findAllTorneos(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Torneo o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Torneo.class).getResultList();
    }

	public static Torneo findTorneo(Long id) {
        if (id == null) return null;
        return entityManager().find(Torneo.class, id);
    }

	public static List<Torneo> findTorneoEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Torneo o", Torneo.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<Torneo> findTorneoEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Torneo o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Torneo.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Torneo attached = Torneo.findTorneo(this.id);
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
    public Torneo merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Torneo merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public static Long countFindTorneosByIdSucursalAndStatus(Sucursal idSucursal, Status status) {
        if (idSucursal == null) throw new IllegalArgumentException("The idSucursal argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Torneo.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Torneo AS o WHERE o.idSucursal = :idSucursal AND o.status = :status", Long.class);
        q.setParameter("idSucursal", idSucursal);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindTorneosByStatus(Status status) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Torneo.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Torneo AS o WHERE o.status = :status", Long.class);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static TypedQuery<Torneo> findTorneosByIdSucursalAndStatus(Sucursal idSucursal, Status status) {
        if (idSucursal == null) throw new IllegalArgumentException("The idSucursal argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Torneo.entityManager();
        TypedQuery<Torneo> q = em.createQuery("SELECT o FROM Torneo AS o WHERE o.idSucursal = :idSucursal AND o.status = :status", Torneo.class);
        q.setParameter("idSucursal", idSucursal);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Torneo> findTorneosByIdSucursalAndStatus(Sucursal idSucursal, Status status, String sortFieldName, String sortOrder) {
        if (idSucursal == null) throw new IllegalArgumentException("The idSucursal argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Torneo.entityManager();
        String jpaQuery = "SELECT o FROM Torneo AS o WHERE o.idSucursal = :idSucursal AND o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Torneo> q = em.createQuery(jpaQuery, Torneo.class);
        q.setParameter("idSucursal", idSucursal);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Torneo> findTorneosByStatus(Status status) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Torneo.entityManager();
        TypedQuery<Torneo> q = em.createQuery("SELECT o FROM Torneo AS o WHERE o.status = :status", Torneo.class);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Torneo> findTorneosByStatus(Status status, String sortFieldName, String sortOrder) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Torneo.entityManager();
        String jpaQuery = "SELECT o FROM Torneo AS o WHERE o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Torneo> q = em.createQuery(jpaQuery, Torneo.class);
        q.setParameter("status", status);
        return q;
    }
}
