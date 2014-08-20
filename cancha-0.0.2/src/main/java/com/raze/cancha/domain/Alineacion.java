package com.raze.cancha.domain;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
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
import org.springframework.roo.addon.json.RooJson;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Configurable
@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findAlineacionsByIdPartido" })
@RooJson
public class Alineacion {

    /**
     */
    @ManyToOne
    private Partido idPartido;

    /**
     */
    @ManyToOne
    private Jugador idJugador;

	public String toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }

	public String toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }

	public static Alineacion fromJsonToAlineacion(String json) {
        return new JSONDeserializer<Alineacion>()
        .use(null, Alineacion.class).deserialize(json);
    }

	public static String toJsonArray(Collection<Alineacion> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }

	public static String toJsonArray(Collection<Alineacion> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }

	public static Collection<Alineacion> fromJsonArrayToAlineacions(String json) {
        return new JSONDeserializer<List<Alineacion>>()
        .use("values", Alineacion.class).deserialize(json);
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

	public Partido getIdPartido() {
        return this.idPartido;
    }

	public void setIdPartido(Partido idPartido) {
        this.idPartido = idPartido;
    }

	public Jugador getIdJugador() {
        return this.idJugador;
    }

	public void setIdJugador(Jugador idJugador) {
        this.idJugador = idJugador;
    }

	public static Long countFindAlineacionsByIdPartido(Partido idPartido) {
        if (idPartido == null) throw new IllegalArgumentException("The idPartido argument is required");
        EntityManager em = Alineacion.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Alineacion AS o WHERE o.idPartido = :idPartido", Long.class);
        q.setParameter("idPartido", idPartido);
        return ((Long) q.getSingleResult());
    }

	public static TypedQuery<Alineacion> findAlineacionsByIdPartido(Partido idPartido) {
        if (idPartido == null) throw new IllegalArgumentException("The idPartido argument is required");
        EntityManager em = Alineacion.entityManager();
        TypedQuery<Alineacion> q = em.createQuery("SELECT o FROM Alineacion AS o WHERE o.idPartido = :idPartido", Alineacion.class);
        q.setParameter("idPartido", idPartido);
        return q;
    }

	public static TypedQuery<Alineacion> findAlineacionsByIdPartido(Partido idPartido, String sortFieldName, String sortOrder) {
        if (idPartido == null) throw new IllegalArgumentException("The idPartido argument is required");
        EntityManager em = Alineacion.entityManager();
        String jpaQuery = "SELECT o FROM Alineacion AS o WHERE o.idPartido = :idPartido";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Alineacion> q = em.createQuery(jpaQuery, Alineacion.class);
        q.setParameter("idPartido", idPartido);
        return q;
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("idPartido", "idJugador");

	public static final EntityManager entityManager() {
        EntityManager em = new Alineacion().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countAlineacions() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Alineacion o", Long.class).getSingleResult();
    }

	public static List<Alineacion> findAllAlineacions() {
        return entityManager().createQuery("SELECT o FROM Alineacion o", Alineacion.class).getResultList();
    }

	public static List<Alineacion> findAllAlineacions(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Alineacion o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Alineacion.class).getResultList();
    }

	public static Alineacion findAlineacion(Long id) {
        if (id == null) return null;
        return entityManager().find(Alineacion.class, id);
    }

	public static List<Alineacion> findAlineacionEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Alineacion o", Alineacion.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<Alineacion> findAlineacionEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Alineacion o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Alineacion.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Alineacion attached = Alineacion.findAlineacion(this.id);
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
    public Alineacion merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Alineacion merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
