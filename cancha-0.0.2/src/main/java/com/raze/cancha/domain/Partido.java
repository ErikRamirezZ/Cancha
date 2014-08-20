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

@Entity
@Configurable
@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findPartidoesByStatus", "findPartidoesByIdCancha", "findPartidoesByIdCanchaAndStatus", "findPartidoesByIdArbitro", "findPartidoesByIdTorneoEquipoLocal", "findPartidoesByIdTorneoEquipoLocalAndStatus", "findPartidoesByIdTorneoEquipoVisitante", "findPartidoesByIdTorneoEquipoVisitanteAndStatus", "findPartidoesByIdTorneoEquipoLocalAndIdTorneoEquipoVisitante", "findPartidoesByIdTorneoEquipoLocalAndIdTorneoEquipoVisitanteAndStatus", "findPartidoesByIdTorneoEquipoVisitanteAndIdEquipoLocal", "findPartidoesByIdTorneoEquipoVisitanteAndIdEquipoLocalAndStatus", "findPartidoesByIdTorneoEquipoVisitanteAndIdEquipoVisitante", "findPartidoesByIdTorneoEquipoVisitanteAndIdEquipoVisitanteAndStatus", "findPartidoesByFechaJuegoBetween", "findPartidoesByFechaJuegoBetweenAndStatus", "findPartidoesByFechaJuegoEquals" })
@RooJson
public class Partido {

    /**
     */
    @ManyToOne
    private Cancha idCancha;

    /**
     */
    @ManyToOne
    private Arbitro idArbitro;

    /**
     */
    @ManyToOne
    private Torneo idTorneoEquipoLocal;

    /**
     */
    @ManyToOne
    private Equipo idEquipoLocal;

    /**
     */
    @ManyToOne
    private Torneo idTorneoEquipoVisitante;

    /**
     */
    @ManyToOne
    private Equipo idEquipoVisitante;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date fechaJuego;

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

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("idCancha", "idArbitro", "idTorneoEquipoLocal", "idEquipoLocal", "idTorneoEquipoVisitante", "idEquipoVisitante", "fechaJuego", "status", "fechaCreacion", "fechaModificacion");

	public static final EntityManager entityManager() {
        EntityManager em = new Partido().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countPartidoes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Partido o", Long.class).getSingleResult();
    }

	public static List<Partido> findAllPartidoes() {
        return entityManager().createQuery("SELECT o FROM Partido o", Partido.class).getResultList();
    }

	public static List<Partido> findAllPartidoes(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Partido o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Partido.class).getResultList();
    }

	public static Partido findPartido(Long id) {
        if (id == null) return null;
        return entityManager().find(Partido.class, id);
    }

	public static List<Partido> findPartidoEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Partido o", Partido.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<Partido> findPartidoEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Partido o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Partido.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Partido attached = Partido.findPartido(this.id);
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
    public Partido merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Partido merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public Cancha getIdCancha() {
        return this.idCancha;
    }

	public void setIdCancha(Cancha idCancha) {
        this.idCancha = idCancha;
    }

	public Arbitro getIdArbitro() {
        return this.idArbitro;
    }

	public void setIdArbitro(Arbitro idArbitro) {
        this.idArbitro = idArbitro;
    }

	public Torneo getIdTorneoEquipoLocal() {
        return this.idTorneoEquipoLocal;
    }

	public void setIdTorneoEquipoLocal(Torneo idTorneoEquipoLocal) {
        this.idTorneoEquipoLocal = idTorneoEquipoLocal;
    }

	public Equipo getIdEquipoLocal() {
        return this.idEquipoLocal;
    }

	public void setIdEquipoLocal(Equipo idEquipoLocal) {
        this.idEquipoLocal = idEquipoLocal;
    }

	public Torneo getIdTorneoEquipoVisitante() {
        return this.idTorneoEquipoVisitante;
    }

	public void setIdTorneoEquipoVisitante(Torneo idTorneoEquipoVisitante) {
        this.idTorneoEquipoVisitante = idTorneoEquipoVisitante;
    }

	public Equipo getIdEquipoVisitante() {
        return this.idEquipoVisitante;
    }

	public void setIdEquipoVisitante(Equipo idEquipoVisitante) {
        this.idEquipoVisitante = idEquipoVisitante;
    }

	public Date getFechaJuego() {
        return this.fechaJuego;
    }

	public void setFechaJuego(Date fechaJuego) {
        this.fechaJuego = fechaJuego;
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

	public static Long countFindPartidoesByFechaJuegoBetween(Date minFechaJuego, Date maxFechaJuego) {
        if (minFechaJuego == null) throw new IllegalArgumentException("The minFechaJuego argument is required");
        if (maxFechaJuego == null) throw new IllegalArgumentException("The maxFechaJuego argument is required");
        EntityManager em = Partido.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Partido AS o WHERE o.fechaJuego BETWEEN :minFechaJuego AND :maxFechaJuego", Long.class);
        q.setParameter("minFechaJuego", minFechaJuego);
        q.setParameter("maxFechaJuego", maxFechaJuego);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindPartidoesByFechaJuegoBetweenAndStatus(Date minFechaJuego, Date maxFechaJuego, Status status) {
        if (minFechaJuego == null) throw new IllegalArgumentException("The minFechaJuego argument is required");
        if (maxFechaJuego == null) throw new IllegalArgumentException("The maxFechaJuego argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Partido.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Partido AS o WHERE o.fechaJuego BETWEEN :minFechaJuego AND :maxFechaJuego  AND o.status = :status", Long.class);
        q.setParameter("minFechaJuego", minFechaJuego);
        q.setParameter("maxFechaJuego", maxFechaJuego);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindPartidoesByFechaJuegoEquals(Date fechaJuego) {
        if (fechaJuego == null) throw new IllegalArgumentException("The fechaJuego argument is required");
        EntityManager em = Partido.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Partido AS o WHERE o.fechaJuego = :fechaJuego", Long.class);
        q.setParameter("fechaJuego", fechaJuego);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindPartidoesByIdArbitro(Arbitro idArbitro) {
        if (idArbitro == null) throw new IllegalArgumentException("The idArbitro argument is required");
        EntityManager em = Partido.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Partido AS o WHERE o.idArbitro = :idArbitro", Long.class);
        q.setParameter("idArbitro", idArbitro);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindPartidoesByIdCancha(Cancha idCancha) {
        if (idCancha == null) throw new IllegalArgumentException("The idCancha argument is required");
        EntityManager em = Partido.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Partido AS o WHERE o.idCancha = :idCancha", Long.class);
        q.setParameter("idCancha", idCancha);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindPartidoesByIdCanchaAndStatus(Cancha idCancha, Status status) {
        if (idCancha == null) throw new IllegalArgumentException("The idCancha argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Partido.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Partido AS o WHERE o.idCancha = :idCancha AND o.status = :status", Long.class);
        q.setParameter("idCancha", idCancha);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindPartidoesByIdTorneoEquipoLocal(Torneo idTorneoEquipoLocal) {
        if (idTorneoEquipoLocal == null) throw new IllegalArgumentException("The idTorneoEquipoLocal argument is required");
        EntityManager em = Partido.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Partido AS o WHERE o.idTorneoEquipoLocal = :idTorneoEquipoLocal", Long.class);
        q.setParameter("idTorneoEquipoLocal", idTorneoEquipoLocal);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindPartidoesByIdTorneoEquipoLocalAndIdTorneoEquipoVisitante(Torneo idTorneoEquipoLocal, Torneo idTorneoEquipoVisitante) {
        if (idTorneoEquipoLocal == null) throw new IllegalArgumentException("The idTorneoEquipoLocal argument is required");
        if (idTorneoEquipoVisitante == null) throw new IllegalArgumentException("The idTorneoEquipoVisitante argument is required");
        EntityManager em = Partido.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Partido AS o WHERE o.idTorneoEquipoLocal = :idTorneoEquipoLocal AND o.idTorneoEquipoVisitante = :idTorneoEquipoVisitante", Long.class);
        q.setParameter("idTorneoEquipoLocal", idTorneoEquipoLocal);
        q.setParameter("idTorneoEquipoVisitante", idTorneoEquipoVisitante);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindPartidoesByIdTorneoEquipoLocalAndIdTorneoEquipoVisitanteAndStatus(Torneo idTorneoEquipoLocal, Torneo idTorneoEquipoVisitante, Status status) {
        if (idTorneoEquipoLocal == null) throw new IllegalArgumentException("The idTorneoEquipoLocal argument is required");
        if (idTorneoEquipoVisitante == null) throw new IllegalArgumentException("The idTorneoEquipoVisitante argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Partido.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Partido AS o WHERE o.idTorneoEquipoLocal = :idTorneoEquipoLocal AND o.idTorneoEquipoVisitante = :idTorneoEquipoVisitante AND o.status = :status", Long.class);
        q.setParameter("idTorneoEquipoLocal", idTorneoEquipoLocal);
        q.setParameter("idTorneoEquipoVisitante", idTorneoEquipoVisitante);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindPartidoesByIdTorneoEquipoLocalAndStatus(Torneo idTorneoEquipoLocal, Status status) {
        if (idTorneoEquipoLocal == null) throw new IllegalArgumentException("The idTorneoEquipoLocal argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Partido.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Partido AS o WHERE o.idTorneoEquipoLocal = :idTorneoEquipoLocal AND o.status = :status", Long.class);
        q.setParameter("idTorneoEquipoLocal", idTorneoEquipoLocal);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindPartidoesByIdTorneoEquipoVisitante(Torneo idTorneoEquipoVisitante) {
        if (idTorneoEquipoVisitante == null) throw new IllegalArgumentException("The idTorneoEquipoVisitante argument is required");
        EntityManager em = Partido.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Partido AS o WHERE o.idTorneoEquipoVisitante = :idTorneoEquipoVisitante", Long.class);
        q.setParameter("idTorneoEquipoVisitante", idTorneoEquipoVisitante);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindPartidoesByIdTorneoEquipoVisitanteAndIdEquipoLocal(Torneo idTorneoEquipoVisitante, Equipo idEquipoLocal) {
        if (idTorneoEquipoVisitante == null) throw new IllegalArgumentException("The idTorneoEquipoVisitante argument is required");
        if (idEquipoLocal == null) throw new IllegalArgumentException("The idEquipoLocal argument is required");
        EntityManager em = Partido.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Partido AS o WHERE o.idTorneoEquipoVisitante = :idTorneoEquipoVisitante AND o.idEquipoLocal = :idEquipoLocal", Long.class);
        q.setParameter("idTorneoEquipoVisitante", idTorneoEquipoVisitante);
        q.setParameter("idEquipoLocal", idEquipoLocal);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindPartidoesByIdTorneoEquipoVisitanteAndIdEquipoLocalAndStatus(Torneo idTorneoEquipoVisitante, Equipo idEquipoLocal, Status status) {
        if (idTorneoEquipoVisitante == null) throw new IllegalArgumentException("The idTorneoEquipoVisitante argument is required");
        if (idEquipoLocal == null) throw new IllegalArgumentException("The idEquipoLocal argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Partido.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Partido AS o WHERE o.idTorneoEquipoVisitante = :idTorneoEquipoVisitante AND o.idEquipoLocal = :idEquipoLocal AND o.status = :status", Long.class);
        q.setParameter("idTorneoEquipoVisitante", idTorneoEquipoVisitante);
        q.setParameter("idEquipoLocal", idEquipoLocal);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindPartidoesByIdTorneoEquipoVisitanteAndIdEquipoVisitante(Torneo idTorneoEquipoVisitante, Equipo idEquipoVisitante) {
        if (idTorneoEquipoVisitante == null) throw new IllegalArgumentException("The idTorneoEquipoVisitante argument is required");
        if (idEquipoVisitante == null) throw new IllegalArgumentException("The idEquipoVisitante argument is required");
        EntityManager em = Partido.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Partido AS o WHERE o.idTorneoEquipoVisitante = :idTorneoEquipoVisitante AND o.idEquipoVisitante = :idEquipoVisitante", Long.class);
        q.setParameter("idTorneoEquipoVisitante", idTorneoEquipoVisitante);
        q.setParameter("idEquipoVisitante", idEquipoVisitante);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindPartidoesByIdTorneoEquipoVisitanteAndIdEquipoVisitanteAndStatus(Torneo idTorneoEquipoVisitante, Equipo idEquipoVisitante, Status status) {
        if (idTorneoEquipoVisitante == null) throw new IllegalArgumentException("The idTorneoEquipoVisitante argument is required");
        if (idEquipoVisitante == null) throw new IllegalArgumentException("The idEquipoVisitante argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Partido.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Partido AS o WHERE o.idTorneoEquipoVisitante = :idTorneoEquipoVisitante AND o.idEquipoVisitante = :idEquipoVisitante AND o.status = :status", Long.class);
        q.setParameter("idTorneoEquipoVisitante", idTorneoEquipoVisitante);
        q.setParameter("idEquipoVisitante", idEquipoVisitante);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindPartidoesByIdTorneoEquipoVisitanteAndStatus(Torneo idTorneoEquipoVisitante, Status status) {
        if (idTorneoEquipoVisitante == null) throw new IllegalArgumentException("The idTorneoEquipoVisitante argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Partido.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Partido AS o WHERE o.idTorneoEquipoVisitante = :idTorneoEquipoVisitante AND o.status = :status", Long.class);
        q.setParameter("idTorneoEquipoVisitante", idTorneoEquipoVisitante);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindPartidoesByStatus(Status status) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Partido.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Partido AS o WHERE o.status = :status", Long.class);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static TypedQuery<Partido> findPartidoesByFechaJuegoBetween(Date minFechaJuego, Date maxFechaJuego) {
        if (minFechaJuego == null) throw new IllegalArgumentException("The minFechaJuego argument is required");
        if (maxFechaJuego == null) throw new IllegalArgumentException("The maxFechaJuego argument is required");
        EntityManager em = Partido.entityManager();
        TypedQuery<Partido> q = em.createQuery("SELECT o FROM Partido AS o WHERE o.fechaJuego BETWEEN :minFechaJuego AND :maxFechaJuego", Partido.class);
        q.setParameter("minFechaJuego", minFechaJuego);
        q.setParameter("maxFechaJuego", maxFechaJuego);
        return q;
    }

	public static TypedQuery<Partido> findPartidoesByFechaJuegoBetween(Date minFechaJuego, Date maxFechaJuego, String sortFieldName, String sortOrder) {
        if (minFechaJuego == null) throw new IllegalArgumentException("The minFechaJuego argument is required");
        if (maxFechaJuego == null) throw new IllegalArgumentException("The maxFechaJuego argument is required");
        EntityManager em = Partido.entityManager();
        String jpaQuery = "SELECT o FROM Partido AS o WHERE o.fechaJuego BETWEEN :minFechaJuego AND :maxFechaJuego";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Partido> q = em.createQuery(jpaQuery, Partido.class);
        q.setParameter("minFechaJuego", minFechaJuego);
        q.setParameter("maxFechaJuego", maxFechaJuego);
        return q;
    }

	public static TypedQuery<Partido> findPartidoesByFechaJuegoBetweenAndStatus(Date minFechaJuego, Date maxFechaJuego, Status status) {
        if (minFechaJuego == null) throw new IllegalArgumentException("The minFechaJuego argument is required");
        if (maxFechaJuego == null) throw new IllegalArgumentException("The maxFechaJuego argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Partido.entityManager();
        TypedQuery<Partido> q = em.createQuery("SELECT o FROM Partido AS o WHERE o.fechaJuego BETWEEN :minFechaJuego AND :maxFechaJuego  AND o.status = :status", Partido.class);
        q.setParameter("minFechaJuego", minFechaJuego);
        q.setParameter("maxFechaJuego", maxFechaJuego);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Partido> findPartidoesByFechaJuegoBetweenAndStatus(Date minFechaJuego, Date maxFechaJuego, Status status, String sortFieldName, String sortOrder) {
        if (minFechaJuego == null) throw new IllegalArgumentException("The minFechaJuego argument is required");
        if (maxFechaJuego == null) throw new IllegalArgumentException("The maxFechaJuego argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Partido.entityManager();
        String jpaQuery = "SELECT o FROM Partido AS o WHERE o.fechaJuego BETWEEN :minFechaJuego AND :maxFechaJuego  AND o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Partido> q = em.createQuery(jpaQuery, Partido.class);
        q.setParameter("minFechaJuego", minFechaJuego);
        q.setParameter("maxFechaJuego", maxFechaJuego);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Partido> findPartidoesByFechaJuegoEquals(Date fechaJuego) {
        if (fechaJuego == null) throw new IllegalArgumentException("The fechaJuego argument is required");
        EntityManager em = Partido.entityManager();
        TypedQuery<Partido> q = em.createQuery("SELECT o FROM Partido AS o WHERE o.fechaJuego = :fechaJuego", Partido.class);
        q.setParameter("fechaJuego", fechaJuego);
        return q;
    }

	public static TypedQuery<Partido> findPartidoesByFechaJuegoEquals(Date fechaJuego, String sortFieldName, String sortOrder) {
        if (fechaJuego == null) throw new IllegalArgumentException("The fechaJuego argument is required");
        EntityManager em = Partido.entityManager();
        String jpaQuery = "SELECT o FROM Partido AS o WHERE o.fechaJuego = :fechaJuego";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Partido> q = em.createQuery(jpaQuery, Partido.class);
        q.setParameter("fechaJuego", fechaJuego);
        return q;
    }

	public static TypedQuery<Partido> findPartidoesByIdArbitro(Arbitro idArbitro) {
        if (idArbitro == null) throw new IllegalArgumentException("The idArbitro argument is required");
        EntityManager em = Partido.entityManager();
        TypedQuery<Partido> q = em.createQuery("SELECT o FROM Partido AS o WHERE o.idArbitro = :idArbitro", Partido.class);
        q.setParameter("idArbitro", idArbitro);
        return q;
    }

	public static TypedQuery<Partido> findPartidoesByIdArbitro(Arbitro idArbitro, String sortFieldName, String sortOrder) {
        if (idArbitro == null) throw new IllegalArgumentException("The idArbitro argument is required");
        EntityManager em = Partido.entityManager();
        String jpaQuery = "SELECT o FROM Partido AS o WHERE o.idArbitro = :idArbitro";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Partido> q = em.createQuery(jpaQuery, Partido.class);
        q.setParameter("idArbitro", idArbitro);
        return q;
    }

	public static TypedQuery<Partido> findPartidoesByIdCancha(Cancha idCancha) {
        if (idCancha == null) throw new IllegalArgumentException("The idCancha argument is required");
        EntityManager em = Partido.entityManager();
        TypedQuery<Partido> q = em.createQuery("SELECT o FROM Partido AS o WHERE o.idCancha = :idCancha", Partido.class);
        q.setParameter("idCancha", idCancha);
        return q;
    }

	public static TypedQuery<Partido> findPartidoesByIdCancha(Cancha idCancha, String sortFieldName, String sortOrder) {
        if (idCancha == null) throw new IllegalArgumentException("The idCancha argument is required");
        EntityManager em = Partido.entityManager();
        String jpaQuery = "SELECT o FROM Partido AS o WHERE o.idCancha = :idCancha";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Partido> q = em.createQuery(jpaQuery, Partido.class);
        q.setParameter("idCancha", idCancha);
        return q;
    }

	public static TypedQuery<Partido> findPartidoesByIdCanchaAndStatus(Cancha idCancha, Status status) {
        if (idCancha == null) throw new IllegalArgumentException("The idCancha argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Partido.entityManager();
        TypedQuery<Partido> q = em.createQuery("SELECT o FROM Partido AS o WHERE o.idCancha = :idCancha AND o.status = :status", Partido.class);
        q.setParameter("idCancha", idCancha);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Partido> findPartidoesByIdCanchaAndStatus(Cancha idCancha, Status status, String sortFieldName, String sortOrder) {
        if (idCancha == null) throw new IllegalArgumentException("The idCancha argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Partido.entityManager();
        String jpaQuery = "SELECT o FROM Partido AS o WHERE o.idCancha = :idCancha AND o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Partido> q = em.createQuery(jpaQuery, Partido.class);
        q.setParameter("idCancha", idCancha);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Partido> findPartidoesByIdTorneoEquipoLocal(Torneo idTorneoEquipoLocal) {
        if (idTorneoEquipoLocal == null) throw new IllegalArgumentException("The idTorneoEquipoLocal argument is required");
        EntityManager em = Partido.entityManager();
        TypedQuery<Partido> q = em.createQuery("SELECT o FROM Partido AS o WHERE o.idTorneoEquipoLocal = :idTorneoEquipoLocal", Partido.class);
        q.setParameter("idTorneoEquipoLocal", idTorneoEquipoLocal);
        return q;
    }

	public static TypedQuery<Partido> findPartidoesByIdTorneoEquipoLocal(Torneo idTorneoEquipoLocal, String sortFieldName, String sortOrder) {
        if (idTorneoEquipoLocal == null) throw new IllegalArgumentException("The idTorneoEquipoLocal argument is required");
        EntityManager em = Partido.entityManager();
        String jpaQuery = "SELECT o FROM Partido AS o WHERE o.idTorneoEquipoLocal = :idTorneoEquipoLocal";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Partido> q = em.createQuery(jpaQuery, Partido.class);
        q.setParameter("idTorneoEquipoLocal", idTorneoEquipoLocal);
        return q;
    }

	public static TypedQuery<Partido> findPartidoesByIdTorneoEquipoLocalAndIdTorneoEquipoVisitante(Torneo idTorneoEquipoLocal, Torneo idTorneoEquipoVisitante) {
        if (idTorneoEquipoLocal == null) throw new IllegalArgumentException("The idTorneoEquipoLocal argument is required");
        if (idTorneoEquipoVisitante == null) throw new IllegalArgumentException("The idTorneoEquipoVisitante argument is required");
        EntityManager em = Partido.entityManager();
        TypedQuery<Partido> q = em.createQuery("SELECT o FROM Partido AS o WHERE o.idTorneoEquipoLocal = :idTorneoEquipoLocal AND o.idTorneoEquipoVisitante = :idTorneoEquipoVisitante", Partido.class);
        q.setParameter("idTorneoEquipoLocal", idTorneoEquipoLocal);
        q.setParameter("idTorneoEquipoVisitante", idTorneoEquipoVisitante);
        return q;
    }

	public static TypedQuery<Partido> findPartidoesByIdTorneoEquipoLocalAndIdTorneoEquipoVisitante(Torneo idTorneoEquipoLocal, Torneo idTorneoEquipoVisitante, String sortFieldName, String sortOrder) {
        if (idTorneoEquipoLocal == null) throw new IllegalArgumentException("The idTorneoEquipoLocal argument is required");
        if (idTorneoEquipoVisitante == null) throw new IllegalArgumentException("The idTorneoEquipoVisitante argument is required");
        EntityManager em = Partido.entityManager();
        String jpaQuery = "SELECT o FROM Partido AS o WHERE o.idTorneoEquipoLocal = :idTorneoEquipoLocal AND o.idTorneoEquipoVisitante = :idTorneoEquipoVisitante";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Partido> q = em.createQuery(jpaQuery, Partido.class);
        q.setParameter("idTorneoEquipoLocal", idTorneoEquipoLocal);
        q.setParameter("idTorneoEquipoVisitante", idTorneoEquipoVisitante);
        return q;
    }

	public static TypedQuery<Partido> findPartidoesByIdTorneoEquipoLocalAndIdTorneoEquipoVisitanteAndStatus(Torneo idTorneoEquipoLocal, Torneo idTorneoEquipoVisitante, Status status) {
        if (idTorneoEquipoLocal == null) throw new IllegalArgumentException("The idTorneoEquipoLocal argument is required");
        if (idTorneoEquipoVisitante == null) throw new IllegalArgumentException("The idTorneoEquipoVisitante argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Partido.entityManager();
        TypedQuery<Partido> q = em.createQuery("SELECT o FROM Partido AS o WHERE o.idTorneoEquipoLocal = :idTorneoEquipoLocal AND o.idTorneoEquipoVisitante = :idTorneoEquipoVisitante AND o.status = :status", Partido.class);
        q.setParameter("idTorneoEquipoLocal", idTorneoEquipoLocal);
        q.setParameter("idTorneoEquipoVisitante", idTorneoEquipoVisitante);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Partido> findPartidoesByIdTorneoEquipoLocalAndIdTorneoEquipoVisitanteAndStatus(Torneo idTorneoEquipoLocal, Torneo idTorneoEquipoVisitante, Status status, String sortFieldName, String sortOrder) {
        if (idTorneoEquipoLocal == null) throw new IllegalArgumentException("The idTorneoEquipoLocal argument is required");
        if (idTorneoEquipoVisitante == null) throw new IllegalArgumentException("The idTorneoEquipoVisitante argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Partido.entityManager();
        String jpaQuery = "SELECT o FROM Partido AS o WHERE o.idTorneoEquipoLocal = :idTorneoEquipoLocal AND o.idTorneoEquipoVisitante = :idTorneoEquipoVisitante AND o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Partido> q = em.createQuery(jpaQuery, Partido.class);
        q.setParameter("idTorneoEquipoLocal", idTorneoEquipoLocal);
        q.setParameter("idTorneoEquipoVisitante", idTorneoEquipoVisitante);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Partido> findPartidoesByIdTorneoEquipoLocalAndStatus(Torneo idTorneoEquipoLocal, Status status) {
        if (idTorneoEquipoLocal == null) throw new IllegalArgumentException("The idTorneoEquipoLocal argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Partido.entityManager();
        TypedQuery<Partido> q = em.createQuery("SELECT o FROM Partido AS o WHERE o.idTorneoEquipoLocal = :idTorneoEquipoLocal AND o.status = :status", Partido.class);
        q.setParameter("idTorneoEquipoLocal", idTorneoEquipoLocal);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Partido> findPartidoesByIdTorneoEquipoLocalAndStatus(Torneo idTorneoEquipoLocal, Status status, String sortFieldName, String sortOrder) {
        if (idTorneoEquipoLocal == null) throw new IllegalArgumentException("The idTorneoEquipoLocal argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Partido.entityManager();
        String jpaQuery = "SELECT o FROM Partido AS o WHERE o.idTorneoEquipoLocal = :idTorneoEquipoLocal AND o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Partido> q = em.createQuery(jpaQuery, Partido.class);
        q.setParameter("idTorneoEquipoLocal", idTorneoEquipoLocal);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Partido> findPartidoesByIdTorneoEquipoVisitante(Torneo idTorneoEquipoVisitante) {
        if (idTorneoEquipoVisitante == null) throw new IllegalArgumentException("The idTorneoEquipoVisitante argument is required");
        EntityManager em = Partido.entityManager();
        TypedQuery<Partido> q = em.createQuery("SELECT o FROM Partido AS o WHERE o.idTorneoEquipoVisitante = :idTorneoEquipoVisitante", Partido.class);
        q.setParameter("idTorneoEquipoVisitante", idTorneoEquipoVisitante);
        return q;
    }

	public static TypedQuery<Partido> findPartidoesByIdTorneoEquipoVisitante(Torneo idTorneoEquipoVisitante, String sortFieldName, String sortOrder) {
        if (idTorneoEquipoVisitante == null) throw new IllegalArgumentException("The idTorneoEquipoVisitante argument is required");
        EntityManager em = Partido.entityManager();
        String jpaQuery = "SELECT o FROM Partido AS o WHERE o.idTorneoEquipoVisitante = :idTorneoEquipoVisitante";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Partido> q = em.createQuery(jpaQuery, Partido.class);
        q.setParameter("idTorneoEquipoVisitante", idTorneoEquipoVisitante);
        return q;
    }

	public static TypedQuery<Partido> findPartidoesByIdTorneoEquipoVisitanteAndIdEquipoLocal(Torneo idTorneoEquipoVisitante, Equipo idEquipoLocal) {
        if (idTorneoEquipoVisitante == null) throw new IllegalArgumentException("The idTorneoEquipoVisitante argument is required");
        if (idEquipoLocal == null) throw new IllegalArgumentException("The idEquipoLocal argument is required");
        EntityManager em = Partido.entityManager();
        TypedQuery<Partido> q = em.createQuery("SELECT o FROM Partido AS o WHERE o.idTorneoEquipoVisitante = :idTorneoEquipoVisitante AND o.idEquipoLocal = :idEquipoLocal", Partido.class);
        q.setParameter("idTorneoEquipoVisitante", idTorneoEquipoVisitante);
        q.setParameter("idEquipoLocal", idEquipoLocal);
        return q;
    }

	public static TypedQuery<Partido> findPartidoesByIdTorneoEquipoVisitanteAndIdEquipoLocal(Torneo idTorneoEquipoVisitante, Equipo idEquipoLocal, String sortFieldName, String sortOrder) {
        if (idTorneoEquipoVisitante == null) throw new IllegalArgumentException("The idTorneoEquipoVisitante argument is required");
        if (idEquipoLocal == null) throw new IllegalArgumentException("The idEquipoLocal argument is required");
        EntityManager em = Partido.entityManager();
        String jpaQuery = "SELECT o FROM Partido AS o WHERE o.idTorneoEquipoVisitante = :idTorneoEquipoVisitante AND o.idEquipoLocal = :idEquipoLocal";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Partido> q = em.createQuery(jpaQuery, Partido.class);
        q.setParameter("idTorneoEquipoVisitante", idTorneoEquipoVisitante);
        q.setParameter("idEquipoLocal", idEquipoLocal);
        return q;
    }

	public static TypedQuery<Partido> findPartidoesByIdTorneoEquipoVisitanteAndIdEquipoLocalAndStatus(Torneo idTorneoEquipoVisitante, Equipo idEquipoLocal, Status status) {
        if (idTorneoEquipoVisitante == null) throw new IllegalArgumentException("The idTorneoEquipoVisitante argument is required");
        if (idEquipoLocal == null) throw new IllegalArgumentException("The idEquipoLocal argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Partido.entityManager();
        TypedQuery<Partido> q = em.createQuery("SELECT o FROM Partido AS o WHERE o.idTorneoEquipoVisitante = :idTorneoEquipoVisitante AND o.idEquipoLocal = :idEquipoLocal AND o.status = :status", Partido.class);
        q.setParameter("idTorneoEquipoVisitante", idTorneoEquipoVisitante);
        q.setParameter("idEquipoLocal", idEquipoLocal);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Partido> findPartidoesByIdTorneoEquipoVisitanteAndIdEquipoLocalAndStatus(Torneo idTorneoEquipoVisitante, Equipo idEquipoLocal, Status status, String sortFieldName, String sortOrder) {
        if (idTorneoEquipoVisitante == null) throw new IllegalArgumentException("The idTorneoEquipoVisitante argument is required");
        if (idEquipoLocal == null) throw new IllegalArgumentException("The idEquipoLocal argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Partido.entityManager();
        String jpaQuery = "SELECT o FROM Partido AS o WHERE o.idTorneoEquipoVisitante = :idTorneoEquipoVisitante AND o.idEquipoLocal = :idEquipoLocal AND o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Partido> q = em.createQuery(jpaQuery, Partido.class);
        q.setParameter("idTorneoEquipoVisitante", idTorneoEquipoVisitante);
        q.setParameter("idEquipoLocal", idEquipoLocal);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Partido> findPartidoesByIdTorneoEquipoVisitanteAndIdEquipoVisitante(Torneo idTorneoEquipoVisitante, Equipo idEquipoVisitante) {
        if (idTorneoEquipoVisitante == null) throw new IllegalArgumentException("The idTorneoEquipoVisitante argument is required");
        if (idEquipoVisitante == null) throw new IllegalArgumentException("The idEquipoVisitante argument is required");
        EntityManager em = Partido.entityManager();
        TypedQuery<Partido> q = em.createQuery("SELECT o FROM Partido AS o WHERE o.idTorneoEquipoVisitante = :idTorneoEquipoVisitante AND o.idEquipoVisitante = :idEquipoVisitante", Partido.class);
        q.setParameter("idTorneoEquipoVisitante", idTorneoEquipoVisitante);
        q.setParameter("idEquipoVisitante", idEquipoVisitante);
        return q;
    }

	public static TypedQuery<Partido> findPartidoesByIdTorneoEquipoVisitanteAndIdEquipoVisitante(Torneo idTorneoEquipoVisitante, Equipo idEquipoVisitante, String sortFieldName, String sortOrder) {
        if (idTorneoEquipoVisitante == null) throw new IllegalArgumentException("The idTorneoEquipoVisitante argument is required");
        if (idEquipoVisitante == null) throw new IllegalArgumentException("The idEquipoVisitante argument is required");
        EntityManager em = Partido.entityManager();
        String jpaQuery = "SELECT o FROM Partido AS o WHERE o.idTorneoEquipoVisitante = :idTorneoEquipoVisitante AND o.idEquipoVisitante = :idEquipoVisitante";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Partido> q = em.createQuery(jpaQuery, Partido.class);
        q.setParameter("idTorneoEquipoVisitante", idTorneoEquipoVisitante);
        q.setParameter("idEquipoVisitante", idEquipoVisitante);
        return q;
    }

	public static TypedQuery<Partido> findPartidoesByIdTorneoEquipoVisitanteAndIdEquipoVisitanteAndStatus(Torneo idTorneoEquipoVisitante, Equipo idEquipoVisitante, Status status) {
        if (idTorneoEquipoVisitante == null) throw new IllegalArgumentException("The idTorneoEquipoVisitante argument is required");
        if (idEquipoVisitante == null) throw new IllegalArgumentException("The idEquipoVisitante argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Partido.entityManager();
        TypedQuery<Partido> q = em.createQuery("SELECT o FROM Partido AS o WHERE o.idTorneoEquipoVisitante = :idTorneoEquipoVisitante AND o.idEquipoVisitante = :idEquipoVisitante AND o.status = :status", Partido.class);
        q.setParameter("idTorneoEquipoVisitante", idTorneoEquipoVisitante);
        q.setParameter("idEquipoVisitante", idEquipoVisitante);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Partido> findPartidoesByIdTorneoEquipoVisitanteAndIdEquipoVisitanteAndStatus(Torneo idTorneoEquipoVisitante, Equipo idEquipoVisitante, Status status, String sortFieldName, String sortOrder) {
        if (idTorneoEquipoVisitante == null) throw new IllegalArgumentException("The idTorneoEquipoVisitante argument is required");
        if (idEquipoVisitante == null) throw new IllegalArgumentException("The idEquipoVisitante argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Partido.entityManager();
        String jpaQuery = "SELECT o FROM Partido AS o WHERE o.idTorneoEquipoVisitante = :idTorneoEquipoVisitante AND o.idEquipoVisitante = :idEquipoVisitante AND o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Partido> q = em.createQuery(jpaQuery, Partido.class);
        q.setParameter("idTorneoEquipoVisitante", idTorneoEquipoVisitante);
        q.setParameter("idEquipoVisitante", idEquipoVisitante);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Partido> findPartidoesByIdTorneoEquipoVisitanteAndStatus(Torneo idTorneoEquipoVisitante, Status status) {
        if (idTorneoEquipoVisitante == null) throw new IllegalArgumentException("The idTorneoEquipoVisitante argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Partido.entityManager();
        TypedQuery<Partido> q = em.createQuery("SELECT o FROM Partido AS o WHERE o.idTorneoEquipoVisitante = :idTorneoEquipoVisitante AND o.status = :status", Partido.class);
        q.setParameter("idTorneoEquipoVisitante", idTorneoEquipoVisitante);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Partido> findPartidoesByIdTorneoEquipoVisitanteAndStatus(Torneo idTorneoEquipoVisitante, Status status, String sortFieldName, String sortOrder) {
        if (idTorneoEquipoVisitante == null) throw new IllegalArgumentException("The idTorneoEquipoVisitante argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Partido.entityManager();
        String jpaQuery = "SELECT o FROM Partido AS o WHERE o.idTorneoEquipoVisitante = :idTorneoEquipoVisitante AND o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Partido> q = em.createQuery(jpaQuery, Partido.class);
        q.setParameter("idTorneoEquipoVisitante", idTorneoEquipoVisitante);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Partido> findPartidoesByStatus(Status status) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Partido.entityManager();
        TypedQuery<Partido> q = em.createQuery("SELECT o FROM Partido AS o WHERE o.status = :status", Partido.class);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Partido> findPartidoesByStatus(Status status, String sortFieldName, String sortOrder) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Partido.entityManager();
        String jpaQuery = "SELECT o FROM Partido AS o WHERE o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Partido> q = em.createQuery(jpaQuery, Partido.class);
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

	public static Partido fromJsonToPartido(String json) {
        return new JSONDeserializer<Partido>()
        .use(null, Partido.class).deserialize(json);
    }

	public static String toJsonArray(Collection<Partido> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }

	public static String toJsonArray(Collection<Partido> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }

	public static Collection<Partido> fromJsonArrayToPartidoes(String json) {
        return new JSONDeserializer<List<Partido>>()
        .use("values", Partido.class).deserialize(json);
    }
}
