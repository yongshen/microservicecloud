package com.atguigu.springcloud.command;

import com.atguigu.springcloud.entities.Dept;
import com.atguigu.springcloud.service.DeptTestService;
import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCollapserProperties;
import com.netflix.hystrix.HystrixCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 申光远
 * @create 2018-10-27 22:13
 * @desc
 **/
public class DeptCollapseCommand extends HystrixCollapser<List<Dept>, Dept, Long> {

    private DeptTestService deptService;
    private Long deptId;

    public DeptCollapseCommand(DeptTestService deptService, Long deptId) {
        super(Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("deptCollapseCommand"))
                .andCollapserPropertiesDefaults(HystrixCollapserProperties.Setter().withTimerDelayInMilliseconds(100)));
        this.deptId = deptId;
        this.deptService = deptService;
    }

    @Override
    public Long getRequestArgument() {
        return deptId;
    }

    @Override
    protected HystrixCommand<List<Dept>> createCommand(Collection<CollapsedRequest<Dept, Long>> collection) {
        List<Long> deptIds = new ArrayList<>(collection.size());
        deptIds.addAll(collection.stream().map(CollapsedRequest::getArgument).collect(Collectors.toList()));
        return new DeptBatchCommand(deptService, deptIds);
    }

    @Override
    protected void mapResponseToRequests(List<Dept> depts, Collection<CollapsedRequest<Dept, Long>> collection) {
        int count = 0;
        for(CollapsedRequest<Dept, Long> collapsedRequest : collection) {
            Dept dept = depts.get(count++);
            collapsedRequest.setResponse(dept);
        }
    }
}
