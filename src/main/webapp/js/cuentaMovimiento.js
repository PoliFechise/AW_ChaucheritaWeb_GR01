function autoSelectTipoCategoria() {
    var tipoSelect = document.getElementById("tipoCategoria");
    var categoriaSelect = document.getElementById("categoria");
    var categoria = categoriaSelect.value;

    if (categoria) {
        var categoriasIngreso = window.categoriasIngreso;
        var categoriasEgreso = window.categoriasEgreso;
        var categoriasTransferencia = window.categoriasTransferencia;

        if (categoriasIngreso.includes(categoria)) {
            tipoSelect.value = 'Ingreso';
        } else if (categoriasEgreso.includes(categoria)) {
            tipoSelect.value = 'Egreso';
        } else if (categoriasTransferencia.includes(categoria)) {
            tipoSelect.value = 'Transferencia';
        }
        document.forms[0].submit();
    }
}

function filterCategorias() {
    var tipoSelect = document.getElementById("tipoCategoria").value;
    var categoriaSelect = document.getElementById("categoria");
    
    var categoriasIngreso = window.categoriasIngreso;
    var categoriasEgreso = window.categoriasEgreso;
    var categoriasTransferencia = window.categoriasTransferencia;

    // Limpiar las opciones actuales
    categoriaSelect.innerHTML = '<option value="">Todas las categorías</option>';

    // Añadir las opciones de categoría según el tipo seleccionado
    var categorias;
    if (tipoSelect === 'Ingreso') {
        categorias = categoriasIngreso;
    } else if (tipoSelect === 'Egreso') {
        categorias = categoriasEgreso;
    } else if (tipoSelect === 'Transferencia') {
        categorias = categoriasTransferencia;
    } else {
        categorias = categoriasIngreso.concat(categoriasEgreso, categoriasTransferencia);
    }

    categorias.forEach(function(cat) {
        var option = document.createElement("option");
        option.value = cat;
        option.text = cat;
        if (cat === window.categoriaSeleccionada) {
            option.selected = true;
        }
        categoriaSelect.appendChild(option);
    });
}

function resetCategoria() {
    filterCategorias();
    var categoriaSelect = document.getElementById("categoria");
    categoriaSelect.value = "";
    document.forms[0].submit();
}

window.onload = filterCategorias;