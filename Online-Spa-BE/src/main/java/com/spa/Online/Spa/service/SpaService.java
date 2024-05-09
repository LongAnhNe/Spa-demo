package com.spa.Online.Spa.service;

import com.spa.Online.Spa.dto.SpaDTO;
import com.spa.Online.Spa.model.Spa;
import com.spa.Online.Spa.model.User;
import com.spa.Online.Spa.request.CreateSpaRequest;

import java.util.List;

public interface SpaService {
    public Spa createSpa(CreateSpaRequest req, User user);
    public Spa updateSpa(Long spaId, CreateSpaRequest updateSpa)throws Exception;
    public void deleteSpa(Long spaId) throws Exception;
    public List<Spa> getAllSpa();
    public List<Spa> searchSpa(String keyWord);
    public Spa findSpaById(Long spaId) throws Exception;
    public Spa getSpaByUserId(Long userId) throws Exception;
    public SpaDTO addToFavorite(Long spaId, User user) throws Exception;
    public Spa updateSpaStatus(Long id)throws Exception;
}
