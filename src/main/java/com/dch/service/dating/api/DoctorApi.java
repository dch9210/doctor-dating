package com.dch.service.dating.api;

import com.dch.service.dating.common.util.RequestUtils;
import com.dch.service.dating.dao.mongodb.doc.DoctorScheduDocument;
import com.dch.service.dating.entity.GetDoctorScheduListReq;
import com.dch.service.dating.service.DoctorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Api(value = "/doctor", tags = {"doctor"})
@RequestMapping("/doctor")
@RestController
public class DoctorApi {

    @Autowired
    private DoctorService doctorService;

    @ApiOperation(value = "医生新增放号")
    @PostMapping("/{doctorId}/realse/schedu")
    public DoctorScheduDocument createDoctorSchedu(@PathVariable("doctorId") String doctorId, @RequestBody DoctorScheduDocument doc, HttpServletRequest req) {
        doc.setDoctorId(doctorId);
        return doctorService.createNewDoctorSchedu(doc);
    }

    @ApiOperation(value = "医生放号列表")
    @PostMapping("/{doctorId}/realse/schedu/list")
    public List<DoctorScheduDocument> getDoctorScheduList(@PathVariable("doctorId") String doctorId, HttpServletRequest req, @RequestBody GetDoctorScheduListReq body) {
        return doctorService.getDoctorScheduList(doctorId, body);
    }

    @ApiOperation(value = "医生删除放号")
    @DeleteMapping("/{doctorId}/realse/schedu/{id}")
    public void deleteDoctorSchedu(@PathVariable("doctorId") String doctorId, @PathVariable("id") String id) {
        doctorService.deleteDoctorSchedu(id);
    }
}
