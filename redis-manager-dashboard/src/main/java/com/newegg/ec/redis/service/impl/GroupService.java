package com.newegg.ec.redis.service.impl;

import com.google.common.base.Strings;
import com.newegg.ec.redis.dao.IGroupDao;
import com.newegg.ec.redis.entity.Group;
import com.newegg.ec.redis.service.IGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Jay.H.Zou
 * @date 2019/9/2
 */
public class GroupService implements IGroupService {

    private static final Logger logger = LoggerFactory.getLogger(GroupService.class);

    @Autowired
    private IGroupDao groupDao;

    @Override
    public List<Group> getAllGroup() {
        try {
            return groupDao.selectAllGroup();
        } catch (Exception e) {
            logger.error("Get all group failed.", e);
            return null;
        }

    }

    @Override
    public List<Group> getGroupByUserId(int userId) {
        try {
            return groupDao.selectGroupByUserId(userId);
        } catch (Exception e) {
            logger.error("Get group by user id failed, user id = " + userId, e);
            return null;
        }

    }

    @Override
    public Group getGroupById(int groupId) {
        try {
            return groupDao.selectGroupById(groupId);
        } catch (Exception e) {
            logger.error("Get group by id failed, group id = " + groupId, e);
            return null;
        }

    }

    @Override
    public boolean addGroup(Group group) {
        try {
            int row = groupDao.insertGroup(group);
            return row > 0;
        } catch (Exception e) {
            logger.error("Add group failed, " + group, e);
            return false;
        }

    }

    @Override
    public boolean groupExist(String groupName) {
        try {
            Group group = groupDao.selectGroupByGroupName(groupName);
            return group != null;
        } catch (Exception e) {
            logger.error("Get group by group name failed, group name = " + groupName, e);
            return true;
        }
    }

    @Override
    public boolean updateGroup(Group group) {
        try {
            int row = groupDao.updateGroup(group);
            return row > 0;
        } catch (Exception e) {
            logger.error("Update group failed, " + group, e);
            return false;
        }
    }

    @Override
    public boolean deleteGroupById(int groupId) {
        try {
            int row = groupDao.deleteGroupById(groupId);
            return row > 0;
        } catch (Exception e) {
            logger.error("Delete group by id, group id = " + groupId, e);
            return false;
        }
    }
}