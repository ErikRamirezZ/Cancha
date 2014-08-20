package com.raze.cancha.domain;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.Version;
import com.raze.cancha.reference.Status;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Configurable
@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findEquipoTorneosByIdEquipo", "findEquipoTorneosByIdEquipoAndStatus", "findEquipoTorneosByIdTorneo", "findEquipoTorneosByIdTorneoAndStatus", "findEquipoTorneosByStatus" })
@RooJson
public class EquipoTorneo {

    /**
     */
    @ManyToOne
    private Equipo idEquipo;

    /**
     */
    @ManyToOne
    private Torneo idTorneo;

    /**
     */
    @ManyToOne
    private Status status;

	public static Long countFindEquipoTorneosByIdEquipo(Equipo idEquipo) {
        if (idEquipo == null) throw new IllegalArgumentException("The idEquipo argument is required");
        EntityManager em = EquipoTorneo.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM EquipoTorneo AS o WHERE o.idEquipo = :idEquipo", Long.class);
        q.setParameter("idEquipo", idEquipo);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindEquipoTorneosByIdEquipoAndStatus(Equipo idEquipo, Status status) {
        if (idEquipo == null) throw new IllegalArgumentException("The idEquipo argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = EquipoTorneo.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM EquipoTorneo AS o WHERE o.idEquipo = :idEquipo AND o.status = :status", Long.class);
        q.setParameter("idEquipo", idEquipo);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindEquipoTorneosByIdTorneo(Torneo idTorneo) {
        if (idTorneo == null) throw new IllegalArgumentException("The idTorneo argument is required");
        EntityManager em = EquipoTorneo.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM EquipoTorneo AS o WHERE o.idTorneo = :idTorneo", Long.class);
        q.setParameter("idTorneo", idTorneo);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindEquipoTorneosByIdTorneoAndStatus(Torneo idTorneo, Status status) {
        if (idTorneo == null) throw new IllegalArgumentException("The idTorneo argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = EquipoTorneo.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM EquipoTorneo AS o WHERE o.idTorneo = :idTorneo AND o.status = :status", Long.class);
        q.setParameter("idTorneo", idTorneo);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindEquipoTorneosByStatus(Status status) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = EquipoTorneo.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM EquipoTorneo AS o WHERE o.status = :status", Long.class);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static TypedQuery<EquipoTorneo> findEquipoTorneosByIdEquipo(Equipo idEquipo) {
        if (idEquipo == null) throw new IllegalArgumentException("The idEquipo argument is required");
        EntityManager em = EquipoTorneo.entityManager();
        TypedQuery<EquipoTorneo> q = em.createQuery("SELECT o FROM EquipoTorneo AS o WHERE o.idEquipo = :idEquipo", EquipoTorneo.class);
        q.setParameter("idEquipo", idEquipo);
        return q;
    }

	public static TypedQuery<EquipoTorneo> findEquipoTorneosByIdEquipo(Equipo idEquipo, String sortFieldName, String sortOrder) {
        if (idEquipo == null) throw new IllegalArgumentException("The idEquipo argument is required");
        EntityManager em = EquipoTorneo.entityManager();
        String jpaQuery = "SELECT o FROM EquipoTorneo AS o WHERE o.idEquipo = :idEquipo";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<EquipoTorneo> q = em.createQuery(jpaQuery, EquipoTorneo.class);
        q.setParameter("idEquipo", idEquipo);
        return q;
    }

	public static TypedQuery<EquipoTorneo> findEquipoTorneosByIdEquipoAndStatus(Equipo idEquipo, Status status) {
        if (idEquipo == null) throw new IllegalArgumentException("The idEquipo argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = EquipoTorneo.entityManager();
        TypedQuery<EquipoTorneo> q = em.createQuery("SELECT o FROM EquipoTorneo AS o WHERE o.idEquipo = :idEquipo AND o.status = :status", EquipoTorneo.class);
        q.setParameter("idEquipo", idEquipo);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<EquipoTorneo> findEquipoTorneosByIdEquipoAndStatus(Equipo idEquipo, Status status, String sortFieldName, String sortOrder) {
        if (idEquipo == null) throw new IllegalArgumentException("The idEquipo argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = EquipoTorneo.entityManager();
        String jpaQuery = "SELECT o FROM EquipoTorneo AS o WHERE o.idEquipo = :idEquipo AND o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<EquipoTorneo> q = em.createQuery(jpaQuery, EquipoTorneo.class);
        q.setParameter("idEquipo", idEquipo);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<EquipoTorneo> findEquipoTorneosByIdTorneo(Torneo idTorneo) {
        if (idTorneo == null) throw new IllegalArgumentException("The idTorneo argument is required");
        EntityManager em = EquipoTorneo.entityManager();
        TypedQuery<EquipoTorneo> q = em.createQuery("SELECT o FROM EquipoTorneo AS o WHERE o.idTorneo = :idTorneo", EquipoTorneo.class);
        q.setParameter("idTorneo", idTorneo);
        return q;
    }

	public static TypedQuery<EquipoTorneo> findEquipoTorneosByIdTorneo(Torneo idTorneo, String sortFieldName, String sortOrder) {
        if (idTorneo == null) throw new IllegalArgumentException("The idTorneo argument is required");
        EntityManager em = EquipoTorneo.entityManager();
        String jpaQuery = "SELECT o FROM EquipoTorneo AS o WHERE o.idTorneo = :idTorneo";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<EquipoTorneo> q = em.createQuery(jpaQuery, EquipoTorneo.class);
        q.setParameter("idTorneo", idTorneo);
        return q;
    }

	public static TypedQuery<EquipoTorneo> findEquipoTorneosByIdTorneoAndStatus(Torneo idTorneo, Status status) {
        if (idTorneo == null) throw new IllegalArgumentException("The idTorneo argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = EquipoTorneo.entityManager();
        TypedQuery<EquipoTorneo> q = em.createQuery("SELECT o FROM EquipoTorneo AS o WHERE o.idTorneo = :idTorneo AND o.status = :status", EquipoTorneo.class);
        q.setParameter("idTorneo", idTorneo);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<EquipoTorneo> findEquipoTorneosByIdTorneoAndStatus(Torneo idTorneo, Status status, String sortFieldName, String sortOrder) {
        if (idTorneo == null) throw new IllegalArgumentException("The idTorneo argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = EquipoTorneo.entityManager();
        String jpaQuery = "SELECT o FROM EquipoTorneo AS o WHERE o.idTorneo = :idTorneo AND o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<EquipoTorneo> q = em.createQuery(jpaQuery, EquipoTorneo.class);
        q.setParameter("idTorneo", idTorneo);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<EquipoTorneo> findEquipoTorneosByStatus(Status status) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = EquipoTorneo.entityManager();
        TypedQuery<EquipoTorneo> q = em.createQuery("SELECT o FROM EquipoTorneo AS o WHERE o.status = :status", EquipoTorneo.class);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<EquipoTorneo> findEquipoTorneosByStatus(Status status, String sortFieldName, String sortOrder) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = EquipoTorneo.entityManager();
        String jpaQuery = "SELECT o FROM EquipoTorneo AS o WHERE o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<EquipoTorneo> q = em.createQuery(jpaQuery, EquipoTorneo.class);
        q.setParameter("status", status);
        return q;
    }

	public Equipo getIdEquipo() {
        return this.idEquipo;
    }

	public void setIdEquipo(Equipo idEquipo) {
        this.idEquipo = idEquipo;
    }

	public Torneo getIdTorneo() {
        return this.idTorneo;
    }

	public void setIdTorneo(Torneo idTorneo) {
        this.idTorneo = idTorneo;
    }

	public Status getStatus() {
        return this.status;
    }

	public void setStatus(Status status) {
        this.status = status;
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

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("idEquipo", "idTorneo", "status");

	public static final EntityManager entityManager() {
        EntityManager em = new EquipoTorneo().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countEquipoTorneos() {
        return entityManager().createQuery("SELECT COUNT(o) FROM EquipoTorneo o", Long.class).getSingleResult();
    }

	public static List<EquipoTorneo> findAllEquipoTorneos() {
        return entityManager().createQuery("SELECT o FROM EquipoTorneo o", EquipoTorneo.class).getResultList();
    }

	public static List<EquipoTorneo> findAllEquipoTorneos(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM EquipoTorneo o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, EquipoTorneo.class).getResultList();
    }

	public static EquipoTorneo findEquipoTorneo(Long id) {
        if (id == null) return null;
        return entityManager().find(EquipoTorneo.class, id);
    }

	public static List<EquipoTorneo> findEquipoTorneoEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM EquipoTorneo o", EquipoTorneo.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<EquipoTorneo> findEquipoTorneoEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM EquipoTorneo o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, EquipoTorneo.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            EquipoTorneo attached = EquipoTorneo.findEquipoTorneo(this.id);
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
    public EquipoTorneo merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        EquipoTorneo merged = this.entityManager.merge(this);
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

	public static EquipoTorneo fromJsonToEquipoTorneo(String json) {
        return new JSONDeserializer<EquipoTorneo>()
        .use(null, EquipoTorneo.class).deserialize(json);
    }

	public static String toJsonArray(Collection<EquipoTorneo> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }

	public static String toJsonArray(Collection<EquipoTorneo> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }

	public static Collection<EquipoTorneo> fromJsonArrayToEquipoTorneos(String json) {
        return new JSONDeserializer<List<EquipoTorneo>>()
        .use("values", EquipoTorneo.class).deserialize(json);
    }
}
