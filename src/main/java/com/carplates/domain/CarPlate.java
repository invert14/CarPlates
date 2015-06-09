package com.carplates.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * User: sebastianpawlak Date: 23.05.2013
 */
@Entity
@Table(name = "carplates")
public class CarPlate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "registrationAuthorityId")
    private RegistrationAuthority registrationAuthority;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name="carplates_owners",
//            joinColumns={@JoinColumn(name="carplateid")},
//            inverseJoinColumns={@JoinColumn(name="ownerid")})
//    private Set<Owner> owners;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "carplates_owners", joinColumns = @JoinColumn(name = "carplateid"))
    @Column(name = "ownerpesel")
    private List<String> pesels;

    private String registrationNumber;

    private String carBrand;

    private String carModel;

    private String vin;

    private Date firstRegistrationDate;

    private Date registrationDate;

    private Date registrationExpirationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RegistrationAuthority getRegistrationAuthority() {
        return registrationAuthority;
    }

    public void setRegistrationAuthority(RegistrationAuthority registrationAuthority) {
        this.registrationAuthority = registrationAuthority;
    }

//    public Set<Owner> getOwners() {
//        return owners;
//    }
//    public void setOwners(Set<Owner> owners) {
//        this.owners = owners;
//    }
    public List<String> getPesels() {
        return pesels;
    }

    public void setPesels(List<String> pesels) {
        this.pesels = pesels;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Date getFirstRegistrationDate() {
        return firstRegistrationDate;
    }

    public void setFirstRegistrationDate(Date firstRegistrationDate) {
        this.firstRegistrationDate = firstRegistrationDate;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getRegistrationExpirationDate() {
        return registrationExpirationDate;
    }

    public void setRegistrationExpirationDate(Date registrationExpirationDate) {
        this.registrationExpirationDate = registrationExpirationDate;
    }

//    public List<Owner> getOwnersList() {
//        return new ArrayList<Owner>(getOwners());
//    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CarPlate carPlate = (CarPlate) o;

        if (id != null ? !id.equals(carPlate.id) : carPlate.id != null) {
            return false;
        }
        if (registrationNumber != null ? !registrationNumber.equals(carPlate.registrationNumber) : carPlate.registrationNumber != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
