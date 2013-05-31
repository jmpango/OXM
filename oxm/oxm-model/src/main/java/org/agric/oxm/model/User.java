package org.agric.oxm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.agric.oxm.model.enums.CategoryOfHouseHold;
import org.agric.oxm.model.enums.Gender;
import org.agric.oxm.model.enums.MaritalStatus;
import org.agric.oxm.model.enums.UserStatus;
import org.apache.commons.lang.StringUtils;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseData implements Comparable<User> {

	private String name, username, password, salt, clearTextPassword;
	private String address, phone1, phone2;

	private Set<Role> roles;

	private UserStatus status;
	private Gender gender;
	private Date dateOfBirth, dateOfJoining, dateCreated;
	private Integer age, yearOfJoining;
	private MaritalStatus maritalStatus;
	private CategoryOfHouseHold categoryOfHouseHold;

	private User createdBy;

	private District district;
	private SubCounty subCounty;
	private Parish parish;
	private Village village;
	private GisCordinate gisCordinates;
	private Double landSize;
	private List<LandUse> landUses;
	private ProducerOrganisation producerOrg;

	private byte[] profilePicture = new byte[1];

	public User() {
		super();
	}

	public User(ProducerOrganisation pOrg) {
		super();
		this.setProducerOrg(pOrg);
		this.setDistrict(pOrg.getDistrict());
		this.setSubCounty(pOrg.getSubCounty());
		this.setParish(pOrg.getParish());
		this.setVillage(pOrg.getVillage());
	}

	public User(ProducerOrganisation pOrg, String name) {
		super();
		this.setProducerOrg(pOrg);
		this.setDistrict(pOrg.getDistrict());
		this.setSubCounty(pOrg.getSubCounty());
		this.setParish(pOrg.getParish());
		this.setVillage(pOrg.getVillage());
		this.setName(name);
	}

	@Transient
	public String getClearTextPassword() {
		return clearTextPassword;
	}

	public void setClearTextPassword(String clearTextPassword) {
		this.clearTextPassword = clearTextPassword;
	}

	@Column(name = "username", nullable = false, unique = true)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "role_users", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	public synchronized Set<Role> getRoles() {
		synchronized (this) {
			return roles;
		}
	}

	@Transient
	public String getRolesString() {
		String str = "";
		if (roles != null) {
			int i = 0;

			for (Iterator<Role> rit = roles.iterator(); rit.hasNext();) {
				Role role = rit.next();
				String roleNameToDisplay = role.getName();

				if (role.getName().toUpperCase().contains("ROLE_"))
					roleNameToDisplay = role.getName().substring(5);

				if (StringUtils.isBlank(str))
					str += roleNameToDisplay;
				else
					str += ", " + roleNameToDisplay;
				i++;
				if (i == 2)
					break;
			}
			if (roles.size() > 2)
				str += ", and " + (roles.size() - 2) + " more";
		}
		return str;
	}

	public synchronized void setRoles(Set<Role> roles) {
		synchronized (this) {
			this.roles = roles;
		}
	}

	public synchronized void addRole(Role role) {
		synchronized (this) {
			if (roles == null) {
				roles = new HashSet<Role>();
			}

			if (!this.roles.contains(role)) {
				roles.add(role);
				role.addUser(this);
			}
		}
	}

	public synchronized void removeRole(Role role) {
		synchronized (this) {
			if (roles != null) {
				for (Role r : roles) {
					if (r.getName().equals(role.getName())) {
						roles.remove(role);
						break;
					}
				}
			}
		}
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "user_status", nullable = false)
	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	@Column(name = "salt", nullable = false)
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "gender", nullable = true)
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_of_birth", nullable = true)
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name = "address", nullable = true)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Transient
	public String getAddressString() {
		if (StringUtils.isNotBlank(address))
			return address;

		if (village != null)
			return village.getFullName();
		if (parish != null)
			return parish.getFullName();
		if (subCounty != null)
			return subCounty.getFullName();
		if (district != null)
			return district.getName();
		return "";
	}

	@Column(name = "phone1", nullable = true)
	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	@Column(name = "phone2", nullable = true)
	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	@Lob()
	@Column(name = "picture", nullable = true)
	public byte[] getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(byte[] profilePicture) {
		this.profilePicture = profilePicture;
	}

	@ManyToOne
	@JoinColumn(name = "gis_cordinates_id", nullable = true)
	public GisCordinate getGisCordinates() {
		return gisCordinates;
	}

	public void setGisCordinates(GisCordinate gisCordinates) {
		this.gisCordinates = gisCordinates;
	}

	@ManyToOne
	@JoinColumn(name = "district_id", nullable = true)
	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	@ManyToOne
	@JoinColumn(name = "subcounty_id", nullable = true)
	public SubCounty getSubCounty() {
		return subCounty;
	}

	public void setSubCounty(SubCounty subCounty) {
		this.subCounty = subCounty;
	}

	@ManyToOne
	@JoinColumn(name = "parish_id", nullable = true)
	public Parish getParish() {
		return parish;
	}

	public void setParish(Parish parish) {
		this.parish = parish;
	}

	@ManyToOne
	@JoinColumn(name = "village_id", nullable = true)
	public Village getVillage() {
		return village;
	}

	public void setVillage(Village village) {
		this.village = village;
	}

	@Column(name = "land_size", nullable = true)
	public Double getLandSize() {
		return landSize;
	}

	public void setLandSize(Double landSize) {
		this.landSize = landSize;
	}

	public void addLand(LandUse landUse) {
		if (landUse == null) {
			return;
		}

		if (this.getLandUses() == null) {
			this.setLandUses(new ArrayList<LandUse>());
		}

		this.getLandUses().add(landUse);
		landUse.setProducer(this);
	}

	public void removeLand(LandUse landUse) {
		if (landUse == null || this.getLandUses() == null) {
			return;
		}

		this.getLandUses().remove(landUse);
	}

	@OneToMany(mappedBy = "producer", cascade = { CascadeType.ALL }, orphanRemoval = true)
	public List<LandUse> getLandUses() {
		return landUses;
	}

	public void setLandUses(List<LandUse> landUses) {
		this.landUses = landUses;
	}

	@ManyToOne
	@JoinColumn(name = "producer_organisation_id", nullable = true)
	public ProducerOrganisation getProducerOrg() {
		return producerOrg;
	}

	public void setProducerOrg(ProducerOrganisation producerOrg) {
		this.producerOrg = producerOrg;
	}

	@Column(name = "date_of_joining", nullable = true)
	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	@Column(name = "date_created", nullable = true)
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Column(name = "age", nullable = true)
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "year_of_joining", nullable = true)
	public Integer getYearOfJoining() {
		return yearOfJoining;
	}

	public void setYearOfJoining(Integer yearOfJoining) {
		this.yearOfJoining = yearOfJoining;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "marital_status", nullable = true)
	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "category_of_house_hold", nullable = true)
	public CategoryOfHouseHold getCategoryOfHouseHold() {
		return categoryOfHouseHold;
	}

	public void setCategoryOfHouseHold(CategoryOfHouseHold categoryOfHouseHold) {
		this.categoryOfHouseHold = categoryOfHouseHold;
	}

	@ManyToOne
	@JoinColumn(name = "uesr_id", nullable = true)
	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public boolean hasNewPassword() {
		return (clearTextPassword != null && clearTextPassword.trim().length() > 0);
	}

	public List<Permission> findPermissions() {
		List<Permission> permissions = null;
		if (roles != null && roles.size() > 0) {
			permissions = new ArrayList<Permission>();
			for (Role role : roles) {
				if (role.getPermissions() != null
						&& role.getPermissions().size() > 0) {
					for (Permission perm : role.getPermissions()) {
						permissions.add(perm);
					}
				}
			}
		}
		return permissions;
	}

	public boolean hasAdministrativePrivileges() {
		if (roles != null) {
			for (Role role : roles) {
				if (role.checkIfDefaultAdminRole()) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int compareTo(User o) {
		if (this.getUsername() == null || o.getUsername() == null)
			return 0;
		return this.username.compareToIgnoreCase(o.username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (super.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!super.getId().equals(other.getId()))
			return false;
		return true;
	}

}
