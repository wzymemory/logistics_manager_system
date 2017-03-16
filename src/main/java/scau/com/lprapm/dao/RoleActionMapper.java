package scau.com.lprapm.dao;

import org.springframework.stereotype.Repository;
import scau.com.lprapm.entity.RoleAction;
@Repository
public interface RoleActionMapper {
    int deleteByPrimaryKey(Integer raId);

    int insert(RoleAction record);

    int insertSelective(RoleAction record);

    RoleAction selectByPrimaryKey(Integer raId);

    int updateByPrimaryKeySelective(RoleAction record);

    int updateByPrimaryKey(RoleAction record);

    int updateByActionId(int actionId);
}