package org.atguigu.springcloud.service;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component // 不要忘记添加，不要忘记添加
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService>
{
	@Override
	public DeptClientService create(Throwable throwable)
	{
		return new DeptClientService() {
			@Override
			public org.atguigu.springcloud.entities.Dept get(long id)
			{
				return new org.atguigu.springcloud.entities.Dept().setDeptno(id).setDname("该ID：" + id + "没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭")
						.setDb_source("no this database in MySQL");
			}

			@Override
			public List<org.atguigu.springcloud.entities.Dept> list()
			{
				return null;
			}

			@Override
			public boolean add(org.atguigu.springcloud.entities.Dept dept)
			{
				return false;
			}
		};
	}
}
