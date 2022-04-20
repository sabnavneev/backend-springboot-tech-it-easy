package nl.novi.backend.techiteasy.models;

import javax.persistence.*;

@Entity
public class Television {

    public Television(){
        // nodig voor weet ik veel wat
    }

    public Television(String name, String Type, String Brand){
        this.name = name;
        this.type = type;
        this.brand = brand;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;
    private String type;
    private String brand;

//    Double price
//    Double availableSize
//    Double refreshRate
//    String screenType
//    String screenQuality
//    Boolean smartTv
//    Boolean wifi
//    Boolean voiceControl
//    Boolean hdr
//    Boolean bluetooth
//    Boolean ambiLight
//    Integer originalStock
//    Integer sold

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
