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

@Entity
@Configurable
@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findSucursalsByStatus", "findSucursalsByIdEmpresaAndStatus" })
@RooJson
public class Sucursal {

    /**
     */
    @ManyToOne
    private Empresa idEmpresa;

    /**
     */
    private String nombre;

    /**
     */
    private String domicilio;

    /**
     */
    private String telefono;

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

	public static Long countFindSucursalsByIdEmpresaAndStatus(Empresa idEmpresa, Status status) {
        if (idEmpresa == null) throw new IllegalArgumentException("The idEmpresa argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Sucursal.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Sucursal AS o WHERE o.idEmpresa = :idEmpresa AND o.status = :status", Long.class);
        q.setParameter("idEmpresa", idEmpresa);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindSucursalsByStatus(Status status) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Sucursal.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Sucursal AS o WHERE o.status = :status", Long.class);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static TypedQuery<Sucursal> findSucursalsByIdEmpresaAndStatus(Empresa idEmpresa, Status status) {
        if (idEmpresa == null) throw new IllegalArgumentException("The idEmpresa argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Sucursal.entityManager();
        TypedQuery<Sucursal> q = em.createQuery("SELECT o FROM Sucursal AS o WHERE o.idEmpresa = :idEmpresa AND o.status = :status", Sucursal.class);
        q.setParameter("idEmpresa", idEmpresa);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Sucursal> findSucursalsByIdEmpresaAndStatus(Empresa idEmpresa, Status status, String sortFieldName, String sortOrder) {
        if (idEmpresa == null) throw new IllegalArgumentException("The idEmpresa argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Sucursal.entityManager();
        String jpaQuery = "SELECT o FROM Sucursal AS o WHERE o.idEmpresa = :idEmpresa AND o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Sucursal> q = em.createQuery(jpaQuery, Sucursal.class);
        q.setParameter("idEmpresa", idEmpresa);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Sucursal> findSucursalsByStatus(Status status) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Sucursal.entityManager();
        TypedQuery<Sucursal> q = em.createQuery("SELECT o FROM Sucursal AS o WHERE o.status = :status", Sucursal.class);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Sucursal> findSucursalsByStatus(Status status, String sortFieldName, String sortOrder) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Sucursal.entityManager();
        String jpaQuery = "SELECT o FROM Sucursal AS o WHERE o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Sucursal> q = em.createQuery(jpaQuery, Sucursal.class);
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

	public static Sucursal fromJsonToSucursal(String json) {
        return new JSONDeserializer<Sucursal>()
        .use(null, Sucursal.class).deserialize(json);
    }

	public static String toJsonArray(Collection<Sucursal> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }

	public static String toJsonArray(Collection<Sucursal> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }

	public static Collection<Sucursal> fromJsonArrayToSucursals(String json) {
        return new JSONDeserializer<List<Sucursal>>()
        .use("values", Sucursal.class).deserialize(json);
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("idEmpresa", "nombre", "domicilio", "telefono", "status", "fechaCreacion", "fechaModificacion");

	public static final EntityManager entityManager() {
        EntityManager em = new Sucursal().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countSucursals() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Sucursal o", Long.class).getSingleResult();
    }

	public static List<Sucursal> findAllSucursals() {
        return entityManager().createQuery("SELECT o FROM Sucursal o", Sucursal.class).getResultList();
    }

	public static List<Sucursal> findAllSucursals(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Sucursal o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Sucursal.class).getResultList();
    }

	public static Sucursal findSucursal(Long id) {
        if (id == null) return null;
        return entityManager().find(Sucursal.class, id);
    }

	public static List<Sucursal> findSucursalEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Sucursal o", Sucursal.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<Sucursal> findSucursalEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Sucursal o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Sucursal.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Sucursal attached = Sucursal.findSucursal(this.id);
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
    public Sucursal merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Sucursal merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public Empresa getIdEmpresa() {
        return this.idEmpresa;
    }

	public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

	public String getNombre() {
        return this.nombre;
    }

	public void setNombre(String nombre) {
        this.nombre = nombre;
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
}
