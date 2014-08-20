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
@RooJpaActiveRecord(finders = { "findEstadisticasPartidoesByStatus", "findEstadisticasPartidoesByIdPartido", "findEstadisticasPartidoesByIdPartidoAndStatus" })
@RooJson
public class EstadisticasPartido {

    /**
     */
    @ManyToOne
    private Partido idPartido;

    /**
     */
    private int marcadorEquipoLocal;

    /**
     */
    private int marcadorEquipoVisitante;

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

	public String toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }

	public String toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }

	public static EstadisticasPartido fromJsonToEstadisticasPartido(String json) {
        return new JSONDeserializer<EstadisticasPartido>()
        .use(null, EstadisticasPartido.class).deserialize(json);
    }

	public static String toJsonArray(Collection<EstadisticasPartido> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }

	public static String toJsonArray(Collection<EstadisticasPartido> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }

	public static Collection<EstadisticasPartido> fromJsonArrayToEstadisticasPartidoes(String json) {
        return new JSONDeserializer<List<EstadisticasPartido>>()
        .use("values", EstadisticasPartido.class).deserialize(json);
    }

	public static Long countFindEstadisticasPartidoesByIdPartido(Partido idPartido) {
        if (idPartido == null) throw new IllegalArgumentException("The idPartido argument is required");
        EntityManager em = EstadisticasPartido.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM EstadisticasPartido AS o WHERE o.idPartido = :idPartido", Long.class);
        q.setParameter("idPartido", idPartido);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindEstadisticasPartidoesByIdPartidoAndStatus(Partido idPartido, Status status) {
        if (idPartido == null) throw new IllegalArgumentException("The idPartido argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = EstadisticasPartido.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM EstadisticasPartido AS o WHERE o.idPartido = :idPartido AND o.status = :status", Long.class);
        q.setParameter("idPartido", idPartido);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindEstadisticasPartidoesByStatus(Status status) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = EstadisticasPartido.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM EstadisticasPartido AS o WHERE o.status = :status", Long.class);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static TypedQuery<EstadisticasPartido> findEstadisticasPartidoesByIdPartido(Partido idPartido) {
        if (idPartido == null) throw new IllegalArgumentException("The idPartido argument is required");
        EntityManager em = EstadisticasPartido.entityManager();
        TypedQuery<EstadisticasPartido> q = em.createQuery("SELECT o FROM EstadisticasPartido AS o WHERE o.idPartido = :idPartido", EstadisticasPartido.class);
        q.setParameter("idPartido", idPartido);
        return q;
    }

	public static TypedQuery<EstadisticasPartido> findEstadisticasPartidoesByIdPartido(Partido idPartido, String sortFieldName, String sortOrder) {
        if (idPartido == null) throw new IllegalArgumentException("The idPartido argument is required");
        EntityManager em = EstadisticasPartido.entityManager();
        String jpaQuery = "SELECT o FROM EstadisticasPartido AS o WHERE o.idPartido = :idPartido";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<EstadisticasPartido> q = em.createQuery(jpaQuery, EstadisticasPartido.class);
        q.setParameter("idPartido", idPartido);
        return q;
    }

	public static TypedQuery<EstadisticasPartido> findEstadisticasPartidoesByIdPartidoAndStatus(Partido idPartido, Status status) {
        if (idPartido == null) throw new IllegalArgumentException("The idPartido argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = EstadisticasPartido.entityManager();
        TypedQuery<EstadisticasPartido> q = em.createQuery("SELECT o FROM EstadisticasPartido AS o WHERE o.idPartido = :idPartido AND o.status = :status", EstadisticasPartido.class);
        q.setParameter("idPartido", idPartido);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<EstadisticasPartido> findEstadisticasPartidoesByIdPartidoAndStatus(Partido idPartido, Status status, String sortFieldName, String sortOrder) {
        if (idPartido == null) throw new IllegalArgumentException("The idPartido argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = EstadisticasPartido.entityManager();
        String jpaQuery = "SELECT o FROM EstadisticasPartido AS o WHERE o.idPartido = :idPartido AND o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<EstadisticasPartido> q = em.createQuery(jpaQuery, EstadisticasPartido.class);
        q.setParameter("idPartido", idPartido);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<EstadisticasPartido> findEstadisticasPartidoesByStatus(Status status) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = EstadisticasPartido.entityManager();
        TypedQuery<EstadisticasPartido> q = em.createQuery("SELECT o FROM EstadisticasPartido AS o WHERE o.status = :status", EstadisticasPartido.class);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<EstadisticasPartido> findEstadisticasPartidoesByStatus(Status status, String sortFieldName, String sortOrder) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = EstadisticasPartido.entityManager();
        String jpaQuery = "SELECT o FROM EstadisticasPartido AS o WHERE o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<EstadisticasPartido> q = em.createQuery(jpaQuery, EstadisticasPartido.class);
        q.setParameter("status", status);
        return q;
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("idPartido", "marcadorEquipoLocal", "marcadorEquipoVisitante", "observaciones", "status", "fechaCreacion", "fechaModificacion");

	public static final EntityManager entityManager() {
        EntityManager em = new EstadisticasPartido().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countEstadisticasPartidoes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM EstadisticasPartido o", Long.class).getSingleResult();
    }

	public static List<EstadisticasPartido> findAllEstadisticasPartidoes() {
        return entityManager().createQuery("SELECT o FROM EstadisticasPartido o", EstadisticasPartido.class).getResultList();
    }

	public static List<EstadisticasPartido> findAllEstadisticasPartidoes(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM EstadisticasPartido o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, EstadisticasPartido.class).getResultList();
    }

	public static EstadisticasPartido findEstadisticasPartido(Long id) {
        if (id == null) return null;
        return entityManager().find(EstadisticasPartido.class, id);
    }

	public static List<EstadisticasPartido> findEstadisticasPartidoEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM EstadisticasPartido o", EstadisticasPartido.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<EstadisticasPartido> findEstadisticasPartidoEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM EstadisticasPartido o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, EstadisticasPartido.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            EstadisticasPartido attached = EstadisticasPartido.findEstadisticasPartido(this.id);
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
    public EstadisticasPartido merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        EstadisticasPartido merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public Partido getIdPartido() {
        return this.idPartido;
    }

	public void setIdPartido(Partido idPartido) {
        this.idPartido = idPartido;
    }

	public int getMarcadorEquipoLocal() {
        return this.marcadorEquipoLocal;
    }

	public void setMarcadorEquipoLocal(int marcadorEquipoLocal) {
        this.marcadorEquipoLocal = marcadorEquipoLocal;
    }

	public int getMarcadorEquipoVisitante() {
        return this.marcadorEquipoVisitante;
    }

	public void setMarcadorEquipoVisitante(int marcadorEquipoVisitante) {
        this.marcadorEquipoVisitante = marcadorEquipoVisitante;
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

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
