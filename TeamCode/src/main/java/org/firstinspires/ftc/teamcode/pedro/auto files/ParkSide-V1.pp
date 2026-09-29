{
  "startPoint": {
    "x": 61,
    "y": 132,
    "name": "ParkSideStart",
    "locked": false,
    "headingDeg": 90
  },
  "lines": [
    {
      "kind": "compound",
      "id": "line-mukcy4ik-nay7dz",
      "name": "ParkSide-V1",
      "color": "#ffc516",
      "segments": [
        {
          "id": "line-mukcti28-bx0k2v",
          "color": "#ffc516",
          "name": "ParkSideWait",
          "locked": false,
          "waitBeforeMs": 0,
          "waitAfterMs": 5000,
          "waitBeforeName": "",
          "waitAfterName": "",
          "kind": "atomic",
          "endPoint": {
            "x": 61,
            "y": 132
          },
          "controlPoints": [],
          "heading": {
            "type": "linear",
            "startDeg": 270,
            "endDeg": 270
          }
        },
        {
          "kind": "atomic",
          "id": "line-mukcy37p-av1rpt",
          "endPoint": {
            "x": 48.4696629213483,
            "y": 125.02808988764045
          },
          "controlPoints": [
            {
              "x": 64.34494382022471,
              "y": 104.41011235955057
            }
          ],
          "heading": {
            "type": "linear",
            "reverse": false,
            "degrees": 0,
            "startDeg": 270,
            "endDeg": 90
          },
          "color": "#698899",
          "locked": false,
          "waitBeforeMs": 0,
          "waitAfterMs": 2000,
          "waitBeforeName": "",
          "waitAfterName": ""
        },
        {
          "kind": "atomic",
          "id": "line-mukd31me-tloj1e",
          "endPoint": {
            "x": 61,
            "y": 132
          },
          "controlPoints": [
            {
              "x": 64.97191011235955,
              "y": 105.10674157303372
            }
          ],
          "heading": {
            "type": "linear",
            "reverse": true,
            "startDeg": 90,
            "endDeg": 270
          },
          "color": "#6CDB7D",
          "locked": false,
          "waitBeforeMs": 0,
          "waitAfterMs": 2000,
          "waitBeforeName": "",
          "waitAfterName": ""
        },
        {
          "kind": "atomic",
          "id": "line-mukd4g5h-p8v20r",
          "endPoint": {
            "x": 10.022471910112364,
            "y": 116.38764044943821
          },
          "controlPoints": [
            {
              "x": 66.25393258426966,
              "y": 115.90337078651686
            }
          ],
          "heading": {
            "type": "linear",
            "reverse": true,
            "startDeg": 270,
            "endDeg": 180
          },
          "color": "#9797D6",
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
      "lineId": "line-mukcti28-bx0k2v"
    },
    {
      "kind": "path",
      "lineId": "line-mukcy37p-av1rpt"
    },
    {
      "kind": "path",
      "lineId": "line-mukd31me-tloj1e"
    },
    {
      "kind": "path",
      "lineId": "line-mukd4g5h-p8v20r"
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
  "timestamp": "2026-09-27T22:06:46.551Z"
}