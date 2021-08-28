package com.dextra.challenge.makemagic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dextra.challenge.makemagic.domains.Character;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {

}
