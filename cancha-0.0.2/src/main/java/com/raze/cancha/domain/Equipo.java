package com.raze.cancha.domain;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Lob;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.Version;
import org.springframework.roo.classpath.operations.jsr303.RooUploadedFile;
import com.raze.cancha.reference.Status;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@Entity
@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findEquipoesByStatus", "findEquipoesByIdSucursalAndStatus", "findEquipoesByNombreLikeAndStatus" })
@RooJson
public class Equipo {

    /**
     */
    @ManyToOne
    private Sucursal idSucursal;

    /**
     */
    private String nombre;

    /**
     */
    private String nombreCorto;

    /**
     */
    private String nombreLargo;

    /**
     */
    @RooUploadedFile(contentType = "image/jpeg", autoUpload = true)
    @Lob
    private byte[] logo;

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

	public static Long countFindEquipoesByIdSucursalAndStatus(Sucursal idSucursal, Status status) {
        if (idSucursal == null) throw new IllegalArgumentException("The idSucursal argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Equipo.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Equipo AS o WHERE o.idSucursal = :idSucursal AND o.status = :status", Long.class);
        q.setParameter("idSucursal", idSucursal);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindEquipoesByNombreLikeAndStatus(String nombre, Status status) {
        if (nombre == null || nombre.length() == 0) throw new IllegalArgumentException("The nombre argument is required");
        nombre = nombre.replace('*', '%');
        if (nombre.charAt(0) != '%') {
            nombre = "%" + nombre;
        }
        if (nombre.charAt(nombre.length() - 1) != '%') {
            nombre = nombre + "%";
        }
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Equipo.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Equipo AS o WHERE LOWER(o.nombre) LIKE LOWER(:nombre)  AND o.status = :status", Long.class);
        q.setParameter("nombre", nombre);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static Long countFindEquipoesByStatus(Status status) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Equipo.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Equipo AS o WHERE o.status = :status", Long.class);
        q.setParameter("status", status);
        return ((Long) q.getSingleResult());
    }

	public static TypedQuery<Equipo> findEquipoesByIdSucursalAndStatus(Sucursal idSucursal, Status status) {
        if (idSucursal == null) throw new IllegalArgumentException("The idSucursal argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Equipo.entityManager();
        TypedQuery<Equipo> q = em.createQuery("SELECT o FROM Equipo AS o WHERE o.idSucursal = :idSucursal AND o.status = :status", Equipo.class);
        q.setParameter("idSucursal", idSucursal);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Equipo> findEquipoesByIdSucursalAndStatus(Sucursal idSucursal, Status status, String sortFieldName, String sortOrder) {
        if (idSucursal == null) throw new IllegalArgumentException("The idSucursal argument is required");
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Equipo.entityManager();
        String jpaQuery = "SELECT o FROM Equipo AS o WHERE o.idSucursal = :idSucursal AND o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Equipo> q = em.createQuery(jpaQuery, Equipo.class);
        q.setParameter("idSucursal", idSucursal);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Equipo> findEquipoesByNombreLikeAndStatus(String nombre, Status status) {
        if (nombre == null || nombre.length() == 0) throw new IllegalArgumentException("The nombre argument is required");
        nombre = nombre.replace('*', '%');
        if (nombre.charAt(0) != '%') {
            nombre = "%" + nombre;
        }
        if (nombre.charAt(nombre.length() - 1) != '%') {
            nombre = nombre + "%";
        }
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Equipo.entityManager();
        TypedQuery<Equipo> q = em.createQuery("SELECT o FROM Equipo AS o WHERE LOWER(o.nombre) LIKE LOWER(:nombre)  AND o.status = :status", Equipo.class);
        q.setParameter("nombre", nombre);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Equipo> findEquipoesByNombreLikeAndStatus(String nombre, Status status, String sortFieldName, String sortOrder) {
        if (nombre == null || nombre.length() == 0) throw new IllegalArgumentException("The nombre argument is required");
        nombre = nombre.replace('*', '%');
        if (nombre.charAt(0) != '%') {
            nombre = "%" + nombre;
        }
        if (nombre.charAt(nombre.length() - 1) != '%') {
            nombre = nombre + "%";
        }
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Equipo.entityManager();
        String jpaQuery = "SELECT o FROM Equipo AS o WHERE LOWER(o.nombre) LIKE LOWER(:nombre)  AND o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Equipo> q = em.createQuery(jpaQuery, Equipo.class);
        q.setParameter("nombre", nombre);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Equipo> findEquipoesByStatus(Status status) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Equipo.entityManager();
        TypedQuery<Equipo> q = em.createQuery("SELECT o FROM Equipo AS o WHERE o.status = :status", Equipo.class);
        q.setParameter("status", status);
        return q;
    }

	public static TypedQuery<Equipo> findEquipoesByStatus(Status status, String sortFieldName, String sortOrder) {
        if (status == null) throw new IllegalArgumentException("The status argument is required");
        EntityManager em = Equipo.entityManager();
        String jpaQuery = "SELECT o FROM Equipo AS o WHERE o.status = :status";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Equipo> q = em.createQuery(jpaQuery, Equipo.class);
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

	public static Equipo fromJsonToEquipo(String json) {
        return new JSONDeserializer<Equipo>()
        .use(null, Equipo.class).deserialize(json);
    }

	public static String toJsonArray(Collection<Equipo> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }

	public static String toJsonArray(Collection<Equipo> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }

	public static Collection<Equipo> fromJsonArrayToEquipoes(String json) {
        return new JSONDeserializer<List<Equipo>>()
        .use("values", Equipo.class).deserialize(json);
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

	public String getNombreCorto() {
        return this.nombreCorto;
    }

	public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

	public String getNombreLargo() {
        return this.nombreLargo;
    }

	public void setNombreLargo(String nombreLargo) {
        this.nombreLargo = nombreLargo;
    }

	public byte[] getLogo() {
        return this.logo;
    }

	public void setLogo(byte[] logo) {
        this.logo = logo;
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

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("idSucursal", "nombre", "nombreCorto", "nombreLargo", "logo", "status", "fechaCreacion", "fechaModificacion");

	public static final EntityManager entityManager() {
        EntityManager em = new Equipo().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countEquipoes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Equipo o", Long.class).getSingleResult();
    }

	public static List<Equipo> findAllEquipoes() {
        return entityManager().createQuery("SELECT o FROM Equipo o", Equipo.class).getResultList();
    }

	public static List<Equipo> findAllEquipoes(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Equipo o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Equipo.class).getResultList();
    }

	public static Equipo findEquipo(Long id) {
        if (id == null) return null;
        return entityManager().find(Equipo.class, id);
    }

	public static List<Equipo> findEquipoEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Equipo o", Equipo.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<Equipo> findEquipoEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Equipo o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Equipo.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Equipo attached = Equipo.findEquipo(this.id);
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
    public Equipo merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Equipo merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
