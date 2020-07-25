package com.apttus.api;

import com.google.common.base.Objects;

public class RadioStationsRetrieveResponse {

  public String id;
  public String created_at;
  public String updated_at;
  public String external_id;
  public String name;
  public String longitude;
  public String latitude;
  public String altitude;
  public String rank;

  public RadioStationsRetrieveResponse() {
    super();
  }

  public RadioStationsRetrieveResponse(String id, String created_at, String updated_at, String external_id, String name, String longitude, String latitude, String altitude, String rank) {
    this.id = id;
    this.created_at = created_at;
    this.updated_at = updated_at;
    this.external_id = external_id;
    this.name = name;
    this.longitude = longitude;
    this.latitude = latitude;
    this.altitude = altitude;
    this.rank = rank;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RadioStationsRetrieveResponse that = (RadioStationsRetrieveResponse) o;
    return Objects.equal(id, that.id) &&
      Objects.equal(external_id, that.external_id) &&
      Objects.equal(name, that.name) &&
      Objects.equal(longitude, that.longitude) &&
      Objects.equal(latitude, that.latitude) &&
      Objects.equal(altitude, that.altitude) &&
      Objects.equal(rank, that.rank);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id, created_at, updated_at, external_id, name, longitude, latitude, altitude, rank);
  }

  @Override
  public String toString() {
    return "com.apttus.com.apttus.api.RadioStationsRetrieveResponse{" +
      "id='" + id + '\'' +
      ", created_at='" + created_at + '\'' +
      ", updated_at='" + updated_at + '\'' +
      ", external_id='" + external_id + '\'' +
      ", name='" + name + '\'' +
      ", longitude='" + longitude + '\'' +
      ", latitude='" + latitude + '\'' +
      ", altitude='" + altitude + '\'' +
      ", rank='" + rank + '\'' +
      '}';
  }
}
