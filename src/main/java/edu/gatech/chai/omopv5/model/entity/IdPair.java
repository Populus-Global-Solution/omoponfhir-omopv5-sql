package edu.gatech.chai.omopv5.model.entity;

import edu.gatech.chai.omopv5.model.entity.custom.Column;
import edu.gatech.chai.omopv5.model.entity.custom.GeneratedValue;
import edu.gatech.chai.omopv5.model.entity.custom.GenerationType;
import edu.gatech.chai.omopv5.model.entity.custom.Id;
import edu.gatech.chai.omopv5.model.entity.custom.Table;

import java.util.List;

@Table(name = "id_pair")
public class IdPair extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="id_pair_id_seq")
    @Column(name="id_pair_id", nullable=false)
    private Long id;

    @Column(name = "fhir_id")
    private String fhirId;

    @Column(name = "omop_id")
    private Long omopId;

    public IdPair() {}

    public IdPair(String fhirId, Long omopId) {
        this.fhirId = fhirId;
        this.omopId = omopId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFhirId() {
        return fhirId;
    }

    public void setFhirId(String fhirId) {
        this.fhirId = fhirId;
    }

    public Long getOmopId() {
        return omopId;
    }

    public void setOmopId(Long omopId) {
        this.omopId = omopId;
    }

    @Override
    public String getColumnName(String columnVariable) {
        switch (columnVariable) {
            case "fhirId":
                return "fhir_id";
            default:
                return null;
        }
    }

    @Override
    public String getFirstColumnName() {
        return "id_pair_id";
    }

    @Override
    public String getTableName() {
        return "id_pair";
    }

    @Override
    public String getForeignTableName(String foreignVariable) {
        return null;
    }

    @Override
    public String getSqlSelectTableStatement(List<String> parameterList, List<String> valueList) {
        return "select * from id_pair";
    }
}
