package com.thedebuggers.backend.domain.repository;

import com.thedebuggers.backend.domain.entity.TrashCan;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface TrashCanRepository extends JpaRepository<TrashCan, Long> {

    @Query(
            value = "select distinct tc.* from trash_can tc where MBRContains(ST_LINESTRINGFROMTEXT(concat('LINESTRING(',:#{#x1}, ' ', :#{#y1}, ',', :#{#x2},' ',:#{#y2},')'), 4326), tc.location)"
            , nativeQuery = true
    )
    List<TrashCan> getTrashCanList(double x1, double y1, double x2, double y2);

    @Transactional
    @Modifying
    @Query(
            value = "update trash_can tc set tc.location = ST_GEOMFROMTEXT(:#{#point}) where tc.no = :#{#no}"
            , nativeQuery = true
    )
    void updatePoint(long no, Point point);

    Optional<TrashCan> findByNo(long no);


//    @Query(value = "SELECT * FROM trash_can tc where MBRContains(ST_LINESTRINGFROMTEXT(concat( :#{#pointFormat} ), 4326) , tc.location)", nativeQuery = true)
//    List<TrashCan> getTrashCanList(String pointFormat);
}
