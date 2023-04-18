Highcharts.chart('container', {

    title: {
        text: 'Sankey Graph'
    },

    series: [{
        keys: ['from', 'to', 'weight'],
        data: [
            ['A', '1', 5],
            ['A', '2', 3],
            ['B', '1', 2],
            ['B', '2', 4],
            ['1', 'C', 2],
            ['B', 'C', 4]
        ],
        type: 'sankey',
        name: 'Flujo de datos'
    }]

});
