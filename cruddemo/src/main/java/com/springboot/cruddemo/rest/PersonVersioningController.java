package com.springboot.cruddemo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.cruddemo.entity.Name;
import com.springboot.cruddemo.entity.PersonV1;
import com.springboot.cruddemo.entity.PersonV2;

@RestController
@RequestMapping("/api")
public class PersonVersioningController {
	
	
	@GetMapping("v1/person")
	public PersonV1 personV1()
	{
		return new PersonV1("Umor Abdulla");
	}
	
	@GetMapping("v2/person")
	public PersonV2 personV2()
	{
		return new PersonV2(new Name("Umor","Abdulla"));
	}
	
	@GetMapping(value="/person/param",params="version=1")
	public PersonV1 paramV1()
	{
		return new PersonV1("Umor Abdulla");
	}
	
	@GetMapping(value="/person/param",params="version=2")
	public PersonV2 paramV2()
	{
		return new PersonV2(new Name("Umor","Abdulla"));
	}
	
	@GetMapping(value="/person/header",headers="X-API-VERSION=1")
	public PersonV1 headerV1()
	{
		return new PersonV1("Umor Abdulla");
	}
	
	@GetMapping(value="/person/header",headers="X-API-VERSION=2")
	public PersonV2 headerV2()
	{
		return new PersonV2(new Name("Umor","Abdulla"));
	}
	
	@GetMapping(value="/person/produces",produces="application/vnd.company.app-v1+json")
	public PersonV1 producesV1()
	{
		return new PersonV1("Umor Abdulla");
	}
	
	@GetMapping(value="/person/produces",produces="application/vnd.company.app-v2+json")
	public PersonV2 producesV2()
	{
		return new PersonV2(new Name("Umor","Abdulla"));
	}

}
