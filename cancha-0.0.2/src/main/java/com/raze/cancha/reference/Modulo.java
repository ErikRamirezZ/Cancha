package com.raze.cancha.reference;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
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
@RooJpaActiveRecord(finders = { "findModuloesByNombreNotEquals" })
@RooJson
public class Modulo {

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

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("nombre", "descripcion", "fechaCreacion", "fechaModificacion");

	public static final EntityManager entityManager() {
        EntityManager em = new Modulo().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countModuloes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Modulo o", Long.class).getSingleResult();
    }

	public static List<Modulo> findAllModuloes() {
        return entityManager().createQuery("SELECT o FROM Modulo o", Modulo.class).getResultList();
    }

	public static List<Modulo> findAllModuloes(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Modulo o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Modulo.class).getResultList();
    }

	public static Modulo findModulo(Long id) {
        if (id == null) return null;
        return entityManager().find(Modulo.class, id);
    }

	public static List<Modulo> findModuloEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Modulo o", Modulo.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<Modulo> findModuloEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Modulo o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Modulo.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Modulo attached = Modulo.findModulo(this.id);
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
    public Modulo merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Modulo merged = this.entityManager.merge(this);
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

	public static Modulo fromJsonToModulo(String json) {
        return new JSONDeserializer<Modulo>()
        .use(null, Modulo.class).deserialize(json);
    }

	public static String toJsonArray(Collection<Modulo> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }

	public static String toJsonArray(Collection<Modulo> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }

	public static Collection<Modulo> fromJsonArrayToModuloes(String json) {
        return new JSONDeserializer<List<Modulo>>()
        .use("values", Modulo.class).deserialize(json);
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

	public static Long countFindModuloesByNombreNotEquals(String nombre) {
        if (nombre == null || nombre.length() == 0) throw new IllegalArgumentException("The nombre argument is required");
        EntityManager em = Modulo.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Modulo AS o WHERE o.nombre != :nombre", Long.class);
        q.setParameter("nombre", nombre);
        return ((Long) q.getSingleResult());
    }

	public static TypedQuery<Modulo> findModuloesByNombreNotEquals(String nombre) {
        if (nombre == null || nombre.length() == 0) throw new IllegalArgumentException("The nombre argument is required");
        EntityManager em = Modulo.entityManager();
        TypedQuery<Modulo> q = em.createQuery("SELECT o FROM Modulo AS o WHERE o.nombre != :nombre", Modulo.class);
        q.setParameter("nombre", nombre);
        return q;
    }

	public static TypedQuery<Modulo> findModuloesByNombreNotEquals(String nombre, String sortFieldName, String sortOrder) {
        if (nombre == null || nombre.length() == 0) throw new IllegalArgumentException("The nombre argument is required");
        EntityManager em = Modulo.entityManager();
        String jpaQuery = "SELECT o FROM Modulo AS o WHERE o.nombre != :nombre";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Modulo> q = em.createQuery(jpaQuery, Modulo.class);
        q.setParameter("nombre", nombre);
        return q;
    }
}
