package com.zor07.coffeesnob.controller;

import com.zor07.coffeesnob.dto.UnitDto;
import com.zor07.coffeesnob.entity.Unit;
import com.zor07.coffeesnob.service.UnitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/unit")
public class UnitController {

    private final UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @GetMapping
    public String allUnits(Model model) {
        List<UnitDto> units = unitService.findAll()
                .stream()
                .map(UnitDto::toDto)
                .toList();
        model.addAttribute("units", units);
        return "unit/list";
    }

    @GetMapping("/new")
    public String newUnit(Model model) {
        model.addAttribute("unit", UnitDto.toDto(new Unit()));
        return "unit/form";
    }

    @GetMapping("/{unitId}")
    public String edit(@PathVariable Long unitId, Model model) {
        UnitDto unit = UnitDto.toDto(unitService.findById(unitId));
        model.addAttribute("unit", unit);
        return "unit/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute UnitDto unit) {
        unitService.save(unit.toEntity());
        return "redirect:/unit";
    }

    @GetMapping("/{unitId}/delete")
    public String delete(@PathVariable Long unitId) {
        unitService.deleteById(unitId);
        return "redirect:/unit";
    }
}
