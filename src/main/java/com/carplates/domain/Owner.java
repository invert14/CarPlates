package com.carplates.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;

/**
 * User: sebastianpawlak Date: 23.05.2013
 */
@Entity
@Table(name = "owners")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pesel;

    private String firstName;

    private String lastName;

    private String cityAddress;

    private String postCodeAddress;

    private String streetAddress;

    @Transient
    private String region;

    @Transient
    public String getGlobalId() {
        return "" + id + ";" + region;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "owners")
    private Set<CarPlate> carPlates;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCityAddress() {
        return cityAddress;
    }

    public void setCityAddress(String cityAddress) {
        this.cityAddress = cityAddress;
    }

    public String getPostCodeAddress() {
        return postCodeAddress;
    }

    public void setPostCodeAddress(String postCodeAddress) {
        this.postCodeAddress = postCodeAddress;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public Set<CarPlate> getCarPlates() {
        return carPlates;
    }

    public void setCarPlates(Set<CarPlate> carPlates) {
        this.carPlates = carPlates;
    }

    public List<CarPlate> getCarPlatesList() {
        return new ArrayList<CarPlate>(getCarPlates());
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Owner owner = (Owner) o;

        if (id != null ? !id.equals(owner.id) : owner.id != null) {
            return false;
        }
        if (pesel != null ? !pesel.equals(owner.pesel) : owner.pesel != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (pesel != null ? pesel.hashCode() : 0);
        return result;
    }
}
