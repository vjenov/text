package com.mypet.web.adm;

import org.springframework.stereotype.Repository;

import com.mypet.web.adm.Admin;

@Repository
public interface AdminMapper {
	public Admin selectAdminById(Admin param);
	public void insertAdmin(Admin param);
}

