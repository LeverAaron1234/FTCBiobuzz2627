{
  "startPoint": {
    "x": 56,
    "y": 10,
    "headingDeg": 90,
    "locked": true
  },
  "lines": [
    {
      "kind": "compound",
      "id": "line-mukasnz3-r2pd7f",
      "name": "GardenSideAuto",
      "color": "#CD7A5B",
      "segments": [
        {
          "kind": "atomic",
          "id": "line-mukaldaq-q4uvfs",
          "endPoint": {
            "x": 56,
            "y": 10
          },
          "controlPoints": [],
          "heading": {
            "type": "constant",
            "reverse": true,
            "degrees": 90
          },
          "color": "#CD7A5B",
          "locked": false,
          "waitBeforeMs": 0,
          "waitAfterMs": 2000,
          "waitBeforeName": "",
          "waitAfterName": "",
          "name": "Path 0"
        },
        {
          "id": "line-mudd23ip-uxenin",
          "color": "#ffc516",
          "name": "Path 1",
          "locked": false,
          "waitBeforeMs": 0,
          "waitAfterMs": 0,
          "waitBeforeName": "",
          "waitAfterName": "",
          "kind": "atomic",
          "endPoint": {
            "x": 10,
            "y": 10
          },
          "controlPoints": [
            {
              "x": 55.58988764044943,
              "y": 23.66292134831461
            },
            {
              "x": 48.95842696629213,
              "y": 8.662921348314612
            }
          ],
          "heading": {
            "type": "piecewise",
            "startDeg": 0,
            "endDeg": 90,
            "degrees": 0,
            "reverse": false,
            "piecewiseHeading": {
              "segments": [
                {
                  "startProgress": 0,
                  "endProgress": 0.5,
                  "interpolationType": "linear",
                  "reversed": false,
                  "continueFromPrevious": false,
                  "parameters": {
                    "startDeg": 90,
                    "endDeg": 180
                  }
                },
                {
                  "startProgress": 0.5,
                  "endProgress": 1,
                  "interpolationType": "constant",
                  "reversed": false,
                  "continueFromPrevious": true,
                  "parameters": {
                    "degrees": 90
                  }
                }
              ]
            }
          }
        },
        {
          "kind": "atomic",
          "id": "line-muk9x3np-0w5ui0",
          "endPoint": {
            "x": 55.92134831460672,
            "y": 10.602247191011212
          },
          "controlPoints": [
            {
              "x": 24.325280898876404,
              "y": 17.727247191011234
            },
            {
              "x": 49.548314606741556,
              "y": 15.275280898876394
            }
          ],
          "heading": {
            "type": "piecewise",
            "reverse": true,
            "piecewiseHeading": {
              "segments": [
                {
                  "startProgress": 0,
                  "endProgress": 1,
                  "interpolationType": "linear",
                  "reversed": false,
                  "continueFromPrevious": false,
                  "parameters": {
                    "startDeg": 180,
                    "endDeg": 90
                  }
                }
              ]
            }
          },
          "color": "#866CAC",
          "locked": false,
          "waitBeforeMs": 0,
          "waitAfterMs": 2000,
          "waitBeforeName": "",
          "waitAfterName": ""
        },
        {
          "kind": "atomic",
          "id": "line-muka6qka-a7uadi",
          "endPoint": {
            "x": 13.906741573033687,
            "y": 47.17977528089891
          },
          "controlPoints": [],
          "heading": {
            "type": "linear",
            "reverse": false,
            "startDeg": 90,
            "endDeg": 180
          },
          "color": "#CDA566",
          "locked": false,
          "waitBeforeMs": 0,
          "waitAfterMs": 2000,
          "waitBeforeName": "",
          "waitAfterName": ""
        },
        {
          "kind": "atomic",
          "id": "line-mukajlyg-zgse9o",
          "endPoint": {
            "x": 54.75505617977528,
            "y": 10.464044943820237
          },
          "controlPoints": [],
          "heading": {
            "type": "linear",
            "reverse": false,
            "startDeg": 180,
            "endDeg": 90
          },
          "color": "#D6AC5D",
          "locked": false,
          "waitBeforeMs": 0,
          "waitAfterMs": 2000,
          "waitBeforeName": "",
          "waitAfterName": ""
        },
        {
          "kind": "atomic",
          "id": "line-mukatsm9-vyvus9",
          "endPoint": {
            "x": 11.5247191011236,
            "y": 94.85730337078652
          },
          "controlPoints": [
            {
              "x": 26.842696629213485,
              "y": 41.907865168539324
            }
          ],
          "heading": {
            "type": "linear",
            "reverse": true,
            "startDeg": 90,
            "endDeg": 0
          },
          "color": "#95CDB6",
          "locked": false,
          "waitBeforeMs": 0,
          "waitAfterMs": 0,
          "waitBeforeName": "",
          "waitAfterName": ""
        }
      ],
      "waitBeforeMs": 0,
      "waitAfterMs": 0,
      "waitBeforeName": "",
      "waitAfterName": ""
    }
  ],
  "shapes": [
    {
      "id": "triangle-1",
      "name": "Red Goal",
      "vertices": [
        {
          "x": 141.5,
          "y": 70
        },
        {
          "x": 141.5,
          "y": 141.5
        },
        {
          "x": 118.3,
          "y": 141.5
        },
        {
          "x": 135.5,
          "y": 118
        },
        {
          "x": 136.3,
          "y": 70.2
        }
      ],
      "color": "#dc2626",
      "fillColor": "#ff6b6b"
    },
    {
      "id": "triangle-2",
      "name": "Blue Goal",
      "vertices": [
        {
          "x": 6.2,
          "y": 116.9
        },
        {
          "x": 25,
          "y": 141.5
        },
        {
          "x": 0,
          "y": 141.5
        },
        {
          "x": 0,
          "y": 70
        },
        {
          "x": 6,
          "y": 70
        }
      ],
      "color": "#2563eb",
      "fillColor": "#60a5fa"
    }
  ],
  "sequence": [
    {
      "kind": "path",
      "lineId": "line-mukaldaq-q4uvfs"
    },
    {
      "kind": "path",
      "lineId": "line-mudd23ip-uxenin"
    },
    {
      "kind": "path",
      "lineId": "line-muk9x3np-0w5ui0"
    },
    {
      "kind": "path",
      "lineId": "line-muka6qka-a7uadi"
    },
    {
      "kind": "path",
      "lineId": "line-mukajlyg-zgse9o"
    },
    {
      "kind": "path",
      "lineId": "line-mukatsm9-vyvus9"
    }
  ],
  "fieldPoints": [],
  "activePaths": [],
  "settings": {
    "xVelocity": 75,
    "yVelocity": 65,
    "aVelocity": 3.141592653589793,
    "kFriction": 0.1,
    "rWidth": 18,
    "rHeight": 18,
    "safetyMargin": 0.01,
    "maxVelocity": 40,
    "maxAcceleration": 30,
    "maxDeceleration": 30,
    "fieldMap": "biobuzz.webp",
    "robotImage": "/robot.png",
    "showGhostPaths": false,
    "showOnionLayers": false,
    "onionLayerSpacing": 2,
    "onionColor": "#2ac034",
    "onionNextPointOnly": false,
    "showHeadingArrow": true,
    "showCurrentTValue": false,
    "leftPanelWidth": 324,
    "rightPanelWidth": 598,
    "headingArrowLength": 50,
    "headingArrowColor": "#ffffff",
    "headingArrowThickness": 2,
    "pathOpacity": 0.4,
    "leftPanelMinWidth": 0,
    "rightPanelMinWidth": 0,
    "penToolMaxPaths": 8,
    "experimentalFeatures": {
      "optimize": false,
      "curveThrough": false
    }
  },
  "version": "1.5.0",
  "timestamp": "2026-09-27T21:02:01.014Z"
}