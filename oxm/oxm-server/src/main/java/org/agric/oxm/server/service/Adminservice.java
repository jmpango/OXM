/**
 * 
 */
package org.agric.oxm.server.service;

import java.util.List;

import org.agric.oxm.model.District;
import org.agric.oxm.model.Parish;
import org.agric.oxm.model.SubCounty;
import org.agric.oxm.model.Village;
import org.agric.oxm.model.exception.ValidationException;

/**
 * 
 * interface for {@link District}, {@link SubCounty}, {@link Parish},
 * {@link Village}
 * 
 * @author Job
 * 
 */
public interface Adminservice {

	void save(District district) throws ValidationException;

	void validate(District district) throws ValidationException;

	List<District> getDistricts();

	District getDistrictById(String id);

	District getDistrictByName(String name);

	void deleteDistrict(String id);
	List<District> searchDistrict(String query);

	// ---------------------

	void save(SubCounty subCounty) throws ValidationException;

	void validate(SubCounty subCounty) throws ValidationException;

	List<SubCounty> getSubCounties();

	SubCounty getSubCountyById(String id);

	void deleteSubCounty(String id);

	// ------------------------

	void save(Parish parish) throws ValidationException;

	void validate(Parish parish) throws ValidationException;

	List<Parish> getParishes();

	Parish getParishById(String id);

	void deleteParish(String id);

	// -------------------

	void save(Village village) throws ValidationException;

	void validate(Village village) throws ValidationException;

	List<Village> getVillages();

	Village getVillageById(String id);

	void deleteVillage(String id);
}
