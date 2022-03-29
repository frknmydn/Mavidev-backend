package model;
import javax.persistence.*;

@Entity
@Table(name = "tutorials")
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String city;
    private String district;

    public Data(String city, String district) {
    }

    public Data(long id, String city, String district) {
        this.id = id;
        this.city = city;
        this.district = district;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                '}';
    }
}
