package wd.system.controller;


import com.alibaba.excel.EasyExcel;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import wd.city_division.annotation.ControllerEndpoint;
import wd.city_division.common.FebsResponse;
import wd.city_division.controller.BaseController;
import wd.city_division.exception.FebsException;
import wd.system.entity.QueryRequest;
import wd.system.entity.Role;
import wd.system.service.IRoleService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author WangDong
 * @since 2020-04-10
 */
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@Api(tags = "角色管理")
public class RoleController extends BaseController {
    private final IRoleService roleService;

    @GetMapping
    public FebsResponse getAllRoles(Role role) {
        return new FebsResponse().success().data(roleService.findRoles(role));
    }

    @GetMapping("list")
    @RequiresPermissions("role:view")
    public FebsResponse roleList(Role role, QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(this.roleService.findRoles(role, request));
        return new FebsResponse().success().data(dataTable);
    }

    @PostMapping
    @RequiresPermissions("role:add")
    @ControllerEndpoint(operation = "新增角色", exceptionMessage = "新增角色失败")
    public FebsResponse addRole(@Valid Role role) {
        this.roleService.createRole(role);
        return new FebsResponse().success();
    }

    @GetMapping("delete/{roleIds}")
    @RequiresPermissions("role:delete")
    @ControllerEndpoint(operation = "删除角色", exceptionMessage = "删除角色失败")
    public FebsResponse deleteRoles(@NotBlank(message = "{required}") @PathVariable String roleIds) {
        this.roleService.deleteRoles(roleIds);
        return new FebsResponse().success();
    }

    @PostMapping("update")
    @RequiresPermissions("role:update")
    @ControllerEndpoint(operation = "修改角色", exceptionMessage = "修改角色失败")
    public FebsResponse updateRole(Role role) {
        this.roleService.updateRole(role);
        return new FebsResponse().success();
    }

    @GetMapping("excel")
    @RequiresPermissions("role:export")
//    @ControllerEndpoint(exceptionMessage = "导出Excel失败")
    public void export(QueryRequest queryRequest, Role role, HttpServletResponse response) throws FebsException {
        List<Role> roles = this.roleService.findRoles(role, queryRequest).getRecords();
//        ExcelKit.$Export(Role.class, response).downXlsx(roles, false);
        String fileName = "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        EasyExcel.write(fileName, Role.class).sheet("模板").doWrite(roles);
    }
}

