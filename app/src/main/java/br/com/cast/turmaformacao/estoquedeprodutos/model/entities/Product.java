package br.com.cast.turmaformacao.estoquedeprodutos.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Product implements Parcelable {
    @JsonIgnore
    private Long _id;

    @JsonProperty("id")
    private Long web_id;

    @JsonProperty("date")
    private Long date;

    @JsonProperty("image")
    private String image;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String descript;

    @JsonProperty("stock")
    private Long amount;

    @JsonProperty("minimunStock")
    private Long minAmount;

    @JsonProperty("unitaryValue")
    private Double price;

    @Override
    public String toString() {
        return "Product{" +
                "_id=" + _id +
                ", web_id=" + web_id +
                ", date=" + date +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", descript='" + descript + '\'' +
                ", amount=" + amount +
                ", minAmount=" + minAmount +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (_id != null ? !_id.equals(product._id) : product._id != null) return false;
        if (web_id != null ? !web_id.equals(product.web_id) : product.web_id != null) return false;
        if (date != null ? !date.equals(product.date) : product.date != null) return false;
        if (image != null ? !image.equals(product.image) : product.image != null) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (descript != null ? !descript.equals(product.descript) : product.descript != null)
            return false;
        if (amount != null ? !amount.equals(product.amount) : product.amount != null) return false;
        if (minAmount != null ? !minAmount.equals(product.minAmount) : product.minAmount != null)
            return false;
        return !(price != null ? !price.equals(product.price) : product.price != null);

    }

    @Override
    public int hashCode() {
        int result = _id != null ? _id.hashCode() : 0;
        result = 31 * result + (web_id != null ? web_id.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (descript != null ? descript.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (minAmount != null ? minAmount.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public Long getWeb_id() {
        return web_id;
    }

    public void setWeb_id(Long web_id) {
        this.web_id = web_id;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Long minAmount) {
        this.minAmount = minAmount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this._id);
        dest.writeValue(this.web_id);
        dest.writeValue(this.date);
        dest.writeString(this.image);
        dest.writeString(this.name);
        dest.writeString(this.descript);
        dest.writeValue(this.amount);
        dest.writeValue(this.minAmount);
        dest.writeValue(this.price);
    }

    public Product() {
    }

    protected Product(Parcel in) {
        this._id = (Long) in.readValue(Long.class.getClassLoader());
        this.web_id = (Long) in.readValue(Long.class.getClassLoader());
        this.date = (Long) in.readValue(Long.class.getClassLoader());
        this.image = in.readString();
        this.name = in.readString();
        this.descript = in.readString();
        this.amount = (Long) in.readValue(Long.class.getClassLoader());
        this.minAmount = (Long) in.readValue(Long.class.getClassLoader());
        this.price = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
