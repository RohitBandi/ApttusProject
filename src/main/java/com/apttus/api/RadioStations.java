package com.apttus.api;

public class RadioStations {

  public String ID;
  public String created_at;
  public String updated_at;
  public String external_id;
  public String user_id;
  public String name;
  public String longitude;
  public String latitude;
  public String altitude;
  public String rank;
  public String source_type;

  public RadioStations() {
    super();
  }

  public RadioStations(String ID, String created_at, String updated_at, String external_id, String user_id, String name, String longitude, String latitude, String altitude, String rank, String source_type) {
    this.ID = ID;
    this.created_at = created_at;
    this.updated_at = updated_at;
    this.external_id = external_id;
    this.user_id = user_id;
    this.name = name;
    this.longitude = longitude;
    this.latitude = latitude;
    this.altitude = altitude;
    this.rank = rank;
    this.source_type = source_type;
  }

  public static RadioStationsRetrieveResponse convertToRadioResponse(RadioStations stations){
    RadioStationsRetrieveResponse rs1 = new RadioStationsRetrieveResponse();
    rs1.id = stations.ID;
    rs1.updated_at = stations.updated_at;
    rs1.created_at = stations.created_at;
    rs1.altitude = stations.altitude;
    rs1.external_id = stations.external_id;
    rs1.latitude = stations.latitude;
    rs1.longitude = stations.longitude;
    rs1.name = stations.name;
    rs1.rank = stations.rank;

    return rs1;
  }
}
