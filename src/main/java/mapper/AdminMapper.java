package mapper;

import model.Manager;

public interface AdminMapper {

	int validateManager(Manager manager);

	Manager getManagerInfo(Manager manager);

}
