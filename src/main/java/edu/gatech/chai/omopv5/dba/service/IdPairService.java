package edu.gatech.chai.omopv5.dba.service;

import com.google.cloud.bigquery.FieldValueList;
import edu.gatech.chai.omopv5.model.entity.IdPair;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class IdPairService extends BaseEntityServiceImp<IdPair> {

    public IdPairService() {
        super(IdPair.class);
    }

    @Override
    public IdPair construct(ResultSet rs, IdPair entity, String alias) {
        if (entity == null) {
            entity = new IdPair();
        }

        try {
            if (rs.getString("fhir_id") != null) {
                entity.setFhirId(rs.getString("fhir_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return entity;
    }

    @Override
    public IdPair construct(FieldValueList rowResult, IdPair entity, String alias, List<String> columns) {
        return entity;
    }

    /**
     * Finds or creates a new FHIR <-> OMOP mapping
     *
     * @return the mapping
     */
    public Optional<IdPair> findByFhirId(String fhirId) {
        List<IdPair> results = null;
        try {
            results = this.searchEntity("SELECT * FROM id_pair WHERE fhir_id=" + fhirId + ";");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (results.isEmpty()) {
            return Optional.empty();
        }

        return Optional.ofNullable(results.get(0));
    }

    /**
     * Finds a FHIR <-> OMOP mapping from the omop id
     *
     * @return the mapping, or an empty optional
     */
    public Optional<IdPair> findByOmopId(Long omopId) {
        List<IdPair> results = null;
        try {
            results = this.searchEntity("SELECT * FROM id_pair WHERE omop_id=" + omopId + ";");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (results.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(results.get(0));
    }
}
