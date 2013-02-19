package org.agric.oxm.server.dao.impl;

import org.agric.oxm.model.PositionHolder;
import org.agric.oxm.server.dao.PositionHolderDAO;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Job
 * 
 */
@Repository("positionHolderDAO")
public class HibernatePositionHolderDAOImpl extends BaseDAOImpl<PositionHolder>
		implements PositionHolderDAO {

}
