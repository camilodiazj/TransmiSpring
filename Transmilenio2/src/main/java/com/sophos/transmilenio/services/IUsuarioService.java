package com.sophos.transmilenio.services;

import com.sophos.transmilenio.beans.Usuario;

public interface IUsuarioService {
	public Usuario findByUsername(String username);
}
