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
@RooJpaActiveRecord(finders = { "findIncidenciasByStatus", "findIncidenciasByFechaCreacionBetween", "findIncidenciasByFechaCreacionBetweenAndStatus", "findIncidenciasByFechaCreacionEquals", "findIncidenciasByFechaCreacionEqualsAndStatus", "findIncidenciasByIdSucursal", "findIncidenciasByIdSucursalAndStatus", "findIncidenciasByIdUsuario", "findIncidenciasByIdUsuarioAndStatus" })
@RooJson
public class Incidencia {

    /**
     */
    @ManyToOne
    private Sucursal idSucursal;

    /**
     */
    @ManyToOne
    private Usuario idUsuario;

    /**
     */
    private String observaciones;

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

	public Sucursal getIdSucursal() {
        return this.idSucursal;
    }

	public void setIdSucursal(Sucursal idSucursal) {
        this.idSucursal = idSucursal;
    }

	public Usuario getIdUsuario() {
        return this.idUsuario;
    }

	public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

	public String getObservaciones() {
        return this.observaciones;
    }

	public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("idSucursal", "idUsuario", "observaciones", "status", "fechaCreacion", "fechaModificacion");

	public static final EntityManager entityManager() {
        EntityManager em = new Incidencia().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countIncidencias() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Incidencia o", Long.class).getSingleResult();
    }

	public static List<Incidencia> findAllIncidencias() {
        return entityManager().createQuery("SELECT o FROM Incidencia o", Incidencia.class).getResultList();
    }

	public static List<Incidencia> findAllIncidencias(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Incidencia o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Incidencia.class).getResultList();
    }

	public static Incidencia findIncidencia(Long id) {
        if (id == null) return null;
        return entityManager().find(Incidencia.class, id);
    }

	public static List<Incidencia> findIncidenciaEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Incidencia o", Incidencia.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<Incidencia> findIncidenciaEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Incidencia o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Incidencia.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Incidencia attached = Incidencia.findIncidencia(this.id);
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
    public Incidencia merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Incidencia merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	public String toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }

	public String toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }

	public static Incidencia fromJsonToIncidencia(String json) {
        return new JSONDeserializer<Incidencia>()
        .use(null, Incidencia.class).deserialize(json);
    }

	public static String toJsonArray(Collection<Incidencia> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }

	public static String toJsonArray(Collection<Incidencia> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }

	public static Collection<Incidencia> fromJsonArrayToIncidencias(String json) {
        return new JSONDeserializer<List<Incidencia>>()
        .use("values", Incidencia.class).deserialize(json);
    }

	public static Long countFindIncidenciasByFechaCreacionBetween(Date minFechaCreacion, Date maxFechaCreacion) {
        if (minFechaCreacion == null) throw new IllegalArgumentException("The minFechaCreacion argument is required");
        if (maxFechaCreacion == null) throw new IllegalArgumentException("The maxFechaCreacion argument is required");
        EntityManager em = Incidencia.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Incidencia AS o WHERE o.fechaCreacion BETWEEN :minFechaCreacion AND :maxFechaCreacion", Long.class);
        q.setParameter("minFechaCreacion", minFechaCreacion);
        q.setParameter("maxFechaCreacion", maxFechaCreacion);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindIncidenciasByFechaCreacionBetweenAndStatus(Date minFechaCreacion, Date maxFechaCreacion, Status status) {
        if (minFechaCreacion == null) throw new IllegalArgumentException("The minFechaCreacion argument is required");
        if (maxFechaCreacion == null) throw new IllegalArgumentException("The maxFechaCreacion argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Incidencia.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Incidencia AS o WHERE o.fechaCreacion BETWEEN :minFechaCreacion AND :maxFechaCreacion  AND o.status = :status", Long.class);
        q.setParameter("minFechaCreacion", minFechaCreacion);
        q.setParameter("maxFechaCreacion", maxFechaCreacion);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindIncidenciasByFechaCreacionEquals(Date fechaCreacion) {
        if (fechaCreacion == null) throw new IllegalArgumentException("The fechaCreacion argument is required");
        EntityManager em = Incidencia.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Incidencia AS o WHERE o.fechaCreacion = :fechaCreacion", Long.class);
        q.setParameter("fechaCreacion", fechaCreacion);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindIncidenciasByFechaCreacionEqualsAndStatus(Date fechaCreacion, Status status) {
        if (fechaCreacion == null) throw new IllegalArgumentException("The fechaCreacion argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Incidencia.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Incidencia AS o WHERE o.fechaCreacion = :fechaCreacion  AND o.status = :status", Long.class);
        q.setParameter("fechaCreacion", fechaCreacion);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindIncidenciasByIdSucursal(Sucursal idSucursal) {
        if (idSucursal == null) throw new IllegalArgumentException("The idSucursal argument is required");
        EntityManager em = Incidencia.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Incidencia AS o WHERE o.idSucursal = :idSucursal", Long.class);
        q.setParameter("idSucursal", idSucursal);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindIncidenciasByIdSucursalAndStatus(Sucursal idSucursal, Status status) {
        if (idSucursal == null) throw new IllegalArgumentException("The idSucursal argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Incidencia.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Incidencia AS o WHERE o.idSucursal = :idSucursal AND o.status = :status", Long.class);
        q.setParameter("idSucursal", idSucursal);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindIncidenciasByIdUsuario(Usuario idUsuario) {
        if (idUsuario == null) throw new IllegalArgumentException("The idUsuario argument is required");
        EntityManager em = Incidencia.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Incidencia AS o WHERE o.idUsuario = :idUsuario", Long.class);
        q.setParameter("idUsuario", idUsuario);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindIncidenciasByIdUsuarioAndStatus(Usuario idUsuario, Status status) {
        if (idUsuario == null) throw new IllegalArgumentException("The idUsuario argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Incidencia.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Incidencia AS o WHERE o.idUsuario = :idUsuario AND o.status = :status", Long.class);
        q.setParameter("idUsuario", idUsuario);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindIncidenciasByStatus(Status status) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Incidencia.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Incidencia AS o WHERE o.status = :status", Long.class);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static TypedQuery<Incidencia> findIncidenciasByFechaCreacionBetween(Date minFechaCreacion, Date maxFechaCreacion) {
        if (minFechaCreacion == null) throw new IllegalArgumentException("The minFechaCreacion argument is required");
        if (maxFechaCreacion == null) throw new IllegalArgumentException("The maxFechaCreacion argument is required");
        EntityManager em = Incidencia.entityManager();
        TypedQuery<Incidencia> q = em.createQuery("SELECT o FROM Incidencia AS o WHERE o.fechaCreacion BETWEEN :minFechaCreacion AND :maxFechaCreacion", Incidencia.class);
        q.setParameter("minFechaCreacion", minFechaCreacion);
        q.setParameter("maxFechaCreacion", maxFechaCreacion);
        return q;
    }

	public static TypedQuery<Incidencia> findIncidenciasByFechaCreacionBetween(Date minFechaCreacion, Date maxFechaCreacion, String sortFieldName, String sortOrder) {
        if (minFechaCreacion == null) throw new IllegalArgumentException("The minFechaCreacion argument is required");
        if (maxFechaCreacion == null) throw new IllegalArgumentException("The maxFechaCreacion argument is required");
        EntityManager em = Incidencia.entityManager();
        String jpaQuery = "SELECT o FROM Incidencia AS o WHERE o.fechaCreacion BETWEEN :minFechaCreacion AND :maxFechaCreacion";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Incidencia> q = em.createQuery(jpaQuery, Incidencia.class);
        q.setParameter("minFechaCreacion", minFechaCreacion);
        q.setParameter("maxFechaCreacion", maxFechaCreacion);
        return q;
    }

	public static TypedQuery<Incidencia> findIncidenciasByFechaCreacionBetweenAndStatus(Date minFechaCreacion, Date maxFechaCreacion, Status status) {
        if (minFechaCreacion == null) throw new IllegalArgumentException("The minFechaCreacion argument is required");
        if (maxFechaCreacion == null) throw new IllegalArgumentException("The maxFechaCreacion argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Incidencia.entityManager();
        TypedQuery<Incidencia> q = em.createQuery("SELECT o FROM Incidencia AS o WHERE o.fechaCreacion BETWEEN :minFechaCreacion AND :maxFechaCreacion  AND o.status = :status", Incidencia.class);
        q.setParameter("minFechaCreacion", minFechaCreacion);
        q.setParameter("maxFechaCreacion", maxFechaCreacion);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Incidencia> findIncidenciasByFechaCreacionBetweenAndStatus(Date minFechaCreacion, Date maxFechaCreacion, Status status, String sortFieldName, String sortOrder) {
        if (minFechaCreacion == null) throw new IllegalArgumentException("The minFechaCreacion argument is required");
        if (maxFechaCreacion == null) throw new IllegalArgumentException("The maxFechaCreacion argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Incidencia.entityManager();
        String jpaQuery = "SELECT o FROM Incidencia AS o WHERE o.fechaCreacion BETWEEN :minFechaCreacion AND :maxFechaCreacion  AND o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Incidencia> q = em.createQuery(jpaQuery, Incidencia.class);
        q.setParameter("minFechaCreacion", minFechaCreacion);
        q.setParameter("maxFechaCreacion", maxFechaCreacion);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Incidencia> findIncidenciasByFechaCreacionEquals(Date fechaCreacion) {
        if (fechaCreacion == null) throw new IllegalArgumentException("The fechaCreacion argument is required");
        EntityManager em = Incidencia.entityManager();
        TypedQuery<Incidencia> q = em.createQuery("SELECT o FROM Incidencia AS o WHERE o.fechaCreacion = :fechaCreacion", Incidencia.class);
        q.setParameter("fechaCreacion", fechaCreacion);
        return q;
    }

	public static TypedQuery<Incidencia> findIncidenciasByFechaCreacionEquals(Date fechaCreacion, String sortFieldName, String sortOrder) {
        if (fechaCreacion == null) throw new IllegalArgumentException("The fechaCreacion argument is required");
        EntityManager em = Incidencia.entityManager();
        String jpaQuery = "SELECT o FROM Incidencia AS o WHERE o.fechaCreacion = :fechaCreacion";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Incidencia> q = em.createQuery(jpaQuery, Incidencia.class);
        q.setParameter("fechaCreacion", fechaCreacion);
        return q;
    }

	public static TypedQuery<Incidencia> findIncidenciasByFechaCreacionEqualsAndStatus(Date fechaCreacion, Status status) {
        if (fechaCreacion == null) throw new IllegalArgumentException("The fechaCreacion argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Incidencia.entityManager();
        TypedQuery<Incidencia> q = em.createQuery("SELECT o FROM Incidencia AS o WHERE o.fechaCreacion = :fechaCreacion  AND o.status = :status", Incidencia.class);
        q.setParameter("fechaCreacion", fechaCreacion);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Incidencia> findIncidenciasByFechaCreacionEqualsAndStatus(Date fechaCreacion, Status status, String sortFieldName, String sortOrder) {
        if (fechaCreacion == null) throw new IllegalArgumentException("The fechaCreacion argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Incidencia.entityManager();
        String jpaQuery = "SELECT o FROM Incidencia AS o WHERE o.fechaCreacion = :fechaCreacion  AND o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Incidencia> q = em.createQuery(jpaQuery, Incidencia.class);
        q.setParameter("fechaCreacion", fechaCreacion);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Incidencia> findIncidenciasByIdSucursal(Sucursal idSucursal) {
        if (idSucursal == null) throw new IllegalArgumentException("The idSucursal argument is required");
        EntityManager em = Incidencia.entityManager();
        TypedQuery<Incidencia> q = em.createQuery("SELECT o FROM Incidencia AS o WHERE o.idSucursal = :idSucursal", Incidencia.class);
        q.setParameter("idSucursal", idSucursal);
        return q;
    }

	public static TypedQuery<Incidencia> findIncidenciasByIdSucursal(Sucursal idSucursal, String sortFieldName, String sortOrder) {
        if (idSucursal == null) throw new IllegalArgumentException("The idSucursal argument is required");
        EntityManager em = Incidencia.entityManager();
        String jpaQuery = "SELECT o FROM Incidencia AS o WHERE o.idSucursal = :idSucursal";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Incidencia> q = em.createQuery(jpaQuery, Incidencia.class);
        q.setParameter("idSucursal", idSucursal);
        return q;
    }

	public static TypedQuery<Incidencia> findIncidenciasByIdSucursalAndStatus(Sucursal idSucursal, Status status) {
        if (idSucursal == null) throw new IllegalArgumentException("The idSucursal argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Incidencia.entityManager();
        TypedQuery<Incidencia> q = em.createQuery("SELECT o FROM Incidencia AS o WHERE o.idSucursal = :idSucursal AND o.status = :status", Incidencia.class);
        q.setParameter("idSucursal", idSucursal);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Incidencia> findIncidenciasByIdSucursalAndStatus(Sucursal idSucursal, Status status, String sortFieldName, String sortOrder) {
        if (idSucursal == null) throw new IllegalArgumentException("The idSucursal argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Incidencia.entityManager();
        String jpaQuery = "SELECT o FROM Incidencia AS o WHERE o.idSucursal = :idSucursal AND o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Incidencia> q = em.createQuery(jpaQuery, Incidencia.class);
        q.setParameter("idSucursal", idSucursal);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Incidencia> findIncidenciasByIdUsuario(Usuario idUsuario) {
        if (idUsuario == null) throw new IllegalArgumentException("The idUsuario argument is required");
        EntityManager em = Incidencia.entityManager();
        TypedQuery<Incidencia> q = em.createQuery("SELECT o FROM Incidencia AS o WHERE o.idUsuario = :idUsuario", Incidencia.class);
        q.setParameter("idUsuario", idUsuario);
        return q;
    }

	public static TypedQuery<Incidencia> findIncidenciasByIdUsuario(Usuario idUsuario, String sortFieldName, String sortOrder) {
        if (idUsuario == null) throw new IllegalArgumentException("The idUsuario argument is required");
        EntityManager em = Incidencia.entityManager();
        String jpaQuery = "SELECT o FROM Incidencia AS o WHERE o.idUsuario = :idUsuario";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Incidencia> q = em.createQuery(jpaQuery, Incidencia.class);
        q.setParameter("idUsuario", idUsuario);
        return q;
    }

	public static TypedQuery<Incidencia> findIncidenciasByIdUsuarioAndStatus(Usuario idUsuario, Status status) {
        if (idUsuario == null) throw new IllegalArgumentException("The idUsuario argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Incidencia.entityManager();
        TypedQuery<Incidencia> q = em.createQuery("SELECT o FROM Incidencia AS o WHERE o.idUsuario = :idUsuario AND o.status = :status", Incidencia.class);
        q.setParameter("idUsuario", idUsuario);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Incidencia> findIncidenciasByIdUsuarioAndStatus(Usuario idUsuario, Status status, String sortFieldName, String sortOrder) {
        if (idUsuario == null) throw new IllegalArgumentException("The idUsuario argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Incidencia.entityManager();
        String jpaQuery = "SELECT o FROM Incidencia AS o WHERE o.idUsuario = :idUsuario AND o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Incidencia> q = em.createQuery(jpaQuery, Incidencia.class);
        q.setParameter("idUsuario", idUsuario);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Incidencia> findIncidenciasByStatus(Status status) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Incidencia.entityManager();
        TypedQuery<Incidencia> q = em.createQuery("SELECT o FROM Incidencia AS o WHERE o.status = :status", Incidencia.class);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Incidencia> findIncidenciasByStatus(Status status, String sortFieldName, String sortOrder) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Incidencia.entityManager();
        String jpaQuery = "SELECT o FROM Incidencia AS o WHERE o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Incidencia> q = em.createQuery(jpaQuery, Incidencia.class);
        q.setParameter("status", status);
        return q;
    }
}
