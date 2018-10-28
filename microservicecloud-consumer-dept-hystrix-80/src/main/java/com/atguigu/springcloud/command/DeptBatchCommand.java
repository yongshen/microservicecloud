package com.atguigu.springcloud.command;

import com.atguigu.springcloud.entities.Dept;
import com.atguigu.springcloud.service.DeptTestService;
import com.netflix.hystrix.HystrixCommand;
import org.apache.commons.lang.StringUtils;

import java.util.List;

import static com.netflix.hystrix.HystrixCommandGroupKey.Factory.asKey;

/**
 * @author 申光远
 * @create 2018-10-27 22:03
 * @desc ddd
 **/
public class DeptBatchCommand extends HystrixCommand<List<Dept>> {

    DeptTestService deptService;
    List<Long> deptIds;

    public DeptBatchCommand(DeptTestService deptService, List<Long> deptIds) {
        super(Setter.withGroupKey(asKey("deptServiceCommand")));
        this.deptIds = deptIds;
        this.deptService = deptService;
    }

    @Override
    protected List<Dept> run() throws Exception {
        return deptService.getByIds(deptIds);
    }
}
