import DistanceToObstacle from "../utils/DistanceToObstacle";
import DistanceToObstacle2 from "../utils/DistanceToObstacle2";
import SteeringAngleCurrent from "../utils/SteeringAngleCurrent";
import TargetAngle from "../utils/TargetAngle";
import SpeedCurrent from "../utils/SpeedCurrent";
import RelativeSpeed from "../utils/RelativeSpeed";
import DirectionOfObstacle from "../utils/DirectionOfObstacle";
import OutputWheelAngle1 from "../utils/OutputWheelAngle1";
import OutputWheelAngle2 from "../utils/OutputWheelAngle2";
import OutputPendalAngle3 from "../utils/OutputPedalAngle3";

export const avoidCollision = (data) => {
    const distanceToObstacleAvailable = new DistanceToObstacle().getTrimfTrap(data.khoang_cach)
    const distanceToObstacle2Available = new DistanceToObstacle2().getTrimfTrap(data.khoang_cach)
    const steeringAngleCurrentAvailable = new SteeringAngleCurrent().getTrimfTrap(data.goc_lai)
    const targetAngleAvailable = new TargetAngle().getTrimfTrap(data.goc_muc_tieu)
    const speedCurrentAvailable = new SpeedCurrent().getTrimfTrap(data.van_toc_hien_tai)
    const relativeSpeedAvailable = new RelativeSpeed().getTrimfTrap(data.van_toc_tuong_doi)
    const directionOfObstacleAvailable = new DirectionOfObstacle().getTrimfTrap(data.huong_chuong_ngai_vat)

    let resultAngle = []
    if (!(data.khoang_cach / data.van_toc_hien_tai > 5 && data.van_toc_hien_tai < 15)) {
        directionOfObstacleAvailable.forEach((i1) => {
            steeringAngleCurrentAvailable.forEach((i2) => {
                distanceToObstacleAvailable.forEach((i3) => {
                    let label = ''
                    if (i1.label === 'NM' && i2.label === 'NB' && i3.label === 'Z') {
                        label = 'PM'
                    } else if (i1.label === 'NM' && i2.label === 'NB' && i3.label === 'S') {
                        label = 'PS'
                    } else if (i1.label === 'NM' && i2.label === 'NB' && i3.label === 'B') {
                        label = 'Z'
                    } else if (i1.label === 'NM' && i2.label === 'NM' && i3.label === 'Z') {
                        label = 'PM'
                    } else if (i1.label === 'NM' && i2.label === 'NM' && i3.label === 'S') {
                        label = 'PS'
                    } else if (i1.label === 'NM' && i2.label === 'NM' && i3.label === 'B') {
                        label = 'Z'
                    } else if (i1.label === 'NM' && i2.label === 'Z' && i3.label === 'Z') {
                        label = 'PM'
                    } else if (i1.label === 'NM' && i2.label === 'Z' && i3.label === 'S') {
                        label = 'PM'
                    } else if (i1.label === 'NM' && i2.label === 'Z' && i3.label === 'B') {
                        label = 'Z'
                    } else if (i1.label === 'NM' && i2.label === 'PM' && i3.label === 'Z') {
                        label = 'Z'
                    } else if (i1.label === 'NM' && i2.label === 'PM' && i3.label === 'S') {
                        label = 'Z'
                    } else if (i1.label === 'NM' && i2.label === 'PM' && i3.label === 'B') {
                        label = 'Z'
                    } else if (i1.label === 'NM' && i2.label === 'PB' && i3.label === 'Z') {
                        label = 'Z'
                    } else if (i1.label === 'NM' && i2.label === 'PB' && i3.label === 'S') {
                        label = 'Z'
                    } else if (i1.label === 'NM' && i2.label === 'PB' && i3.label === 'B') {
                        label = 'Z'
                    } else if (i1.label === 'NS' && i2.label === 'NB' && i3.label === 'Z') {
                        label = 'PM'
                    } else if (i1.label === 'NS' && i2.label === 'NB' && i3.label === 'S') {
                        label = 'PS'
                    } else if (i1.label === 'NS' && i2.label === 'NB' && i3.label === 'B') {
                        label = 'Z'
                    } else if (i1.label === 'NS' && i2.label === 'NM' && i3.label === 'Z') {
                        label = 'PM'
                    } else if (i1.label === 'NS' && i2.label === 'NM' && i3.label === 'S') {
                        label = 'PS'
                    } else if (i1.label === 'NS' && i2.label === 'NM' && i3.label === 'B') {
                        label = 'PS'
                    } else if (i1.label === 'NS' && i2.label === 'Z' && i3.label === 'Z') {
                        label = 'PM'
                    } else if (i1.label === 'NS' && i2.label === 'Z' && i3.label === 'S') {
                        label = 'PS'
                    } else if (i1.label === 'NS' && i2.label === 'Z' && i3.label === 'B') {
                        label = 'Z'
                    } else if (i1.label === 'NS' && i2.label === 'PM' && i3.label === 'Z') {
                        label = 'Z'
                    } else if (i1.label === 'NS' && i2.label === 'PM' && i3.label === 'S') {
                        label = 'Z'
                    } else if (i1.label === 'NS' && i2.label === 'PM' && i3.label === 'B') {
                        label = 'Z'
                    } else if (i1.label === 'NS' && i2.label === 'PB' && i3.label === 'Z') {
                        label = 'Z'
                    } else if (i1.label === 'NS' && i2.label === 'PB' && i3.label === 'S') {
                        label = 'Z'
                    } else if (i1.label === 'NS' && i2.label === 'PB' && i3.label === 'B') {
                        label = 'Z'
                    } else if (i1.label === 'Z' && i2.label === 'NB' && i3.label === 'Z') {
                        label = 'NS'
                    } else if (i1.label === 'Z' && i2.label === 'NB' && i3.label === 'S') {
                        label = 'Z'
                    } else if (i1.label === 'Z' && i2.label === 'NB' && i3.label === 'B') {
                        label = 'Z'
                    } else if (i1.label === 'Z' && i2.label === 'NM' && i3.label === 'Z') {
                        label = 'Z'
                    } else if (i1.label === 'Z' && i2.label === 'NM' && i3.label === 'S') {
                        label = 'Z'
                    } else if (i1.label === 'Z' && i2.label === 'NM' && i3.label === 'B') {
                        label = 'Z'
                    } else if (i1.label === 'Z' && i2.label === 'Z' && i3.label === 'Z') {
                        label = 'PM'
                    } else if (i1.label === 'Z' && i2.label === 'Z' && i3.label === 'S') {
                        label = 'PM'
                    } else if (i1.label === 'Z' && i2.label === 'Z' && i3.label === 'B') {
                        label = 'Z'
                    } else if (i1.label === 'Z' && i2.label === 'PM' && i3.label === 'Z') {
                        label = 'PS'
                    } else if (i1.label === 'Z' && i2.label === 'PM' && i3.label === 'S') {
                        label = 'Z'
                    } else if (i1.label === 'Z' && i2.label === 'PM' && i3.label === 'B') {
                        label = 'Z'
                    } else if (i1.label === 'Z' && i2.label === 'PB' && i3.label === 'Z') {
                        label = 'Z'
                    } else if (i1.label === 'Z' && i2.label === 'PB' && i3.label === 'S') {
                        label = 'Z'
                    } else if (i1.label === 'Z' && i2.label === 'PB' && i3.label === 'B') {
                        label = 'Z'
                    } else if (i1.label === 'PS' && i2.label === 'NB' && i3.label === 'Z') {
                        label = 'Z'
                    } else if (i1.label === 'PS' && i2.label === 'NB' && i3.label === 'S') {
                        label = 'Z'
                    } else if (i1.label === 'PS' && i2.label === 'NB' && i3.label === 'B') {
                        label = 'Z'
                    } else if (i1.label === 'PS' && i2.label === 'NM' && i3.label === 'Z') {
                        label = 'Z'
                    } else if (i1.label === 'PS' && i2.label === 'NM' && i3.label === 'S') {
                        label = 'Z'
                    } else if (i1.label === 'PS' && i2.label === 'NM' && i3.label === 'B') {
                        label = 'Z'
                    } else if (i1.label === 'PS' && i2.label === 'Z' && i3.label === 'Z') {
                        label = 'NM'
                    } else if (i1.label === 'PS' && i2.label === 'Z' && i3.label === 'S') {
                        label = 'NS'
                    } else if (i1.label === 'PS' && i2.label === 'Z' && i3.label === 'B') {
                        label = 'Z'
                    } else if (i1.label === 'PS' && i2.label === 'PM' && i3.label === 'Z') {
                        label = 'NB'
                    } else if (i1.label === 'PS' && i2.label === 'PM' && i3.label === 'S') {
                        label = 'NS'
                    } else if (i1.label === 'PS' && i2.label === 'PM' && i3.label === 'B') {
                        label = 'NS'
                    } else if (i1.label === 'PS' && i2.label === 'PB' && i3.label === 'Z') {
                        label = 'NM'
                    } else if (i1.label === 'PS' && i2.label === 'PB' && i3.label === 'S') {
                        label = 'NS'
                    } else if (i1.label === 'PS' && i2.label === 'PB' && i3.label === 'B') {
                        label = 'Z'
                    } else if (i1.label === 'PM' && i2.label === 'NB' && i3.label === 'Z') {
                        label = 'Z'
                    } else if (i1.label === 'PM' && i2.label === 'NB' && i3.label === 'S') {
                        label = 'Z'
                    } else if (i1.label === 'PM' && i2.label === 'NB' && i3.label === 'B') {
                        label = 'Z'
                    } else if (i1.label === 'PM' && i2.label === 'NM' && i3.label === 'Z') {
                        label = 'Z'
                    } else if (i1.label === 'PM' && i2.label === 'NM' && i3.label === 'S') {
                        label = 'Z'
                    } else if (i1.label === 'PM' && i2.label === 'NM' && i3.label === 'B') {
                        label = 'Z'
                    } else if (i1.label === 'PM' && i2.label === 'Z' && i3.label === 'Z') {
                        label = 'NM'
                    } else if (i1.label === 'PM' && i2.label === 'Z' && i3.label === 'S') {
                        label = 'NM'
                    } else if (i1.label === 'PM' && i2.label === 'Z' && i3.label === 'B') {
                        label = 'Z'
                    } else if (i1.label === 'PM' && i2.label === 'PM' && i3.label === 'Z') {
                        label = 'NM'
                    } else if (i1.label === 'PM' && i2.label === 'PM' && i3.label === 'S') {
                        label = 'NS'
                    } else if (i1.label === 'PM' && i2.label === 'PM' && i3.label === 'B') {
                        label = 'Z'
                    } else if (i1.label === 'PM' && i2.label === 'PB' && i3.label === 'Z') {
                        label = 'NM'
                    } else if (i1.label === 'PM' && i2.label === 'PB' && i3.label === 'S') {
                        label = 'NS'
                    } else if (i1.label === 'PM' && i2.label === 'PB' && i3.label === 'B') {
                        label = 'Z'
                    }
                    if (label !== '') {
                        const value = Math.min(
                            i1.getValueY(data.huong_chuong_ngai_vat),
                            i2.getValueY(data.goc_lai),
                            i3.getValueY(data.khoang_cach))
                        console.log("🚀 ~ file: avoidCollision.js ~ line 183 ~ distanceToObstacleAvailable.forEach ~ value", value)
                        const x = resultAngle.filter((i) => {
                            return i.label === label
                        })
                        console.log(x)
                        if (x.length) {
                            x[0].value = Math.max(x[0].value, value)
                        } else {
                            resultAngle.push({
                                label: label,
                                value: value
                            })
                            console.log("🚀 ~ file: avoidCollision.js ~ line 194 ~ distanceToObstacleAvailable.forEach ~ resultAngle", resultAngle)
                        }

                    }
                })
            })
        })
    } else {
        steeringAngleCurrentAvailable.forEach((i1) => {
            targetAngleAvailable.forEach((i2) => {
                let label = '';
                if (i1.label === 'NB' && i2.label === 'NB') {
                    label = 'Z';
                } else if (i1.label === 'NB' && i2.label === 'NM') {
                    label = 'Z';
                } else if (i1.label === 'NB' && i2.label === 'Z') {
                    label = 'PM';
                } else if (i1.label === 'NB' && i2.label === 'PM') {
                    label = 'PB';
                } else if (i1.label === 'NB' && i2.label === 'PB') {
                    label = 'PB';
                } else if (i1.label === 'NM' && i2.label === 'NB') {
                    label = 'NM';
                } else if (i1.label === 'NM' && i2.label === 'NM') {
                    label = 'Z';
                } else if (i1.label === 'NM' && i2.label === 'Z') {
                    label = 'PM';
                } else if (i1.label === 'NM' && i2.label === 'PM') {
                    label = 'PM';
                } else if (i1.label === 'NM' && i2.label === 'PB') {
                    label = 'PB';
                } else if (i1.label === 'Z' && i2.label === 'NB') {
                    label = 'NM';
                } else if (i1.label === 'Z' && i2.label === 'NM') {
                    label = 'NM';
                } else if (i1.label === 'Z' && i2.label === 'Z') {
                    label = 'Z';
                } else if (i1.label === 'Z' && i2.label === 'PM') {
                    label = 'PM';
                } else if (i1.label === 'Z' && i2.label === 'PB') {
                    label = 'PB';
                } else if (i1.label === 'PM' && i2.label === 'NB') {
                    label = 'NB';
                } else if (i1.label === 'PM' && i2.label === 'NM') {
                    label = 'NM';
                } else if (i1.label === 'PM' && i2.label === 'Z') {
                    label = 'NM';
                } else if (i1.label === 'PM' && i2.label === 'PM') {
                    label = 'Z';
                } else if (i1.label === 'PM' && i2.label === 'PB') {
                    label = 'PM';
                } else if (i1.label === 'PB' && i2.label === 'NB') {
                    label = 'NB';
                } else if (i1.label === 'PB' && i2.label === 'NM') {
                    label = 'NB';
                } else if (i1.label === 'PB' && i2.label === 'Z') {
                    label = 'NB';
                } else if (i1.label === 'PB' && i2.label === 'PM') {
                    label = 'Z';
                } else if (i1.label === 'PB' && i2.label === 'PB') {
                    label = 'Z';
                }
                if (label !== '') {
                    const x = resultAngle.filter((i) => {
                        return i.label === label;
                    });
                    const value = Math.min(
                        i1.getValueY(data.goc_lai),
                        i2.getValueY(data.goc_muc_tieu),
                    );
                    if (x.length) {
                        x[0].value = Math.max(x[0].value, value);
                    } else {
                        resultAngle.push({
                            label,
                            value,
                        });
                    }
                }
            });
        });
    }

    let resultPedal = []
    speedCurrentAvailable.forEach((i1) => {
        distanceToObstacle2Available.forEach((i2) => {
            relativeSpeedAvailable.forEach((i3) => {
                let label = ''
                if (i1.label === 'Z' && i2.label === 'Z' && i3.label === 'N') {
                    label = 'PS'
                } else if (i1.label === 'Z' && i2.label === 'Z' && i3.label === 'Z') {
                    label = 'PS'
                } else if (i1.label === 'Z' && i2.label === 'Z' && i3.label === 'P') {
                    label = 'Z'
                } else if (i1.label === 'Z' && i2.label === 'S' && i3.label === 'N') {
                    label = 'PB'
                } else if (i1.label === 'Z' && i2.label === 'S' && i3.label === 'Z') {
                    label = 'PS'
                } else if (i1.label === 'Z' && i2.label === 'S' && i3.label === 'P') {
                    label = 'Z'
                } else if (i1.label === 'Z' && i2.label === 'B' && i3.label === 'N') {
                    label = 'PB'
                } else if (i1.label === 'Z' && i2.label === 'B' && i3.label === 'Z') {
                    label = 'PB'
                } else if (i1.label === 'Z' && i2.label === 'B' && i3.label === 'P') {
                    label = 'PB'
                } else if (i1.label === 'S' && i2.label === 'Z' && i3.label === 'N') {
                    label = 'Z'
                } else if (i1.label === 'S' && i2.label === 'Z' && i3.label === 'Z') {
                    label = 'Z'
                } else if (i1.label === 'S' && i2.label === 'Z' && i3.label === 'P') {
                    label = 'NS'
                } else if (i1.label === 'S' && i2.label === 'S' && i3.label === 'N') {
                    label = 'PS'
                } else if (i1.label === 'S' && i2.label === 'S' && i3.label === 'Z') {
                    label = 'PS'
                } else if (i1.label === 'S' && i2.label === 'S' && i3.label === 'P') {
                    label = 'Z'
                } else if (i1.label === 'S' && i2.label === 'B' && i3.label === 'N') {
                    label = 'PS'
                } else if (i1.label === 'S' && i2.label === 'B' && i3.label === 'Z') {
                    label = 'PS'
                } else if (i1.label === 'S' && i2.label === 'B' && i3.label === 'P') {
                    label = 'Z'
                } else if (i1.label === 'M' && i2.label === 'Z' && i3.label === 'N') {
                    label = 'NS'
                } else if (i1.label === 'M' && i2.label === 'Z' && i3.label === 'Z') {
                    label = 'NB'
                } else if (i1.label === 'M' && i2.label === 'Z' && i3.label === 'P') {
                    label = 'NB'
                } else if (i1.label === 'M' && i2.label === 'S' && i3.label === 'N') {
                    label = 'Z'
                } else if (i1.label === 'M' && i2.label === 'S' && i3.label === 'Z') {
                    label = 'NS'
                } else if (i1.label === 'M' && i2.label === 'S' && i3.label === 'P') {
                    label = 'NB'
                } else if (i1.label === 'M' && i2.label === 'B' && i3.label === 'N') {
                    label = 'Z'
                } else if (i1.label === 'M' && i2.label === 'B' && i3.label === 'Z') {
                    label = 'Z'
                } else if (i1.label === 'M' && i2.label === 'B' && i3.label === 'P') {
                    label = 'Z'
                } else if (i1.label === 'B' && i2.label === 'Z' && i3.label === 'N') {
                    label = 'NB'
                } else if (i1.label === 'B' && i2.label === 'Z' && i3.label === 'Z') {
                    label = 'NB'
                } else if (i1.label === 'B' && i2.label === 'Z' && i3.label === 'P') {
                    label = 'NB'
                } else if (i1.label === 'B' && i2.label === 'S' && i3.label === 'N') {
                    label = 'NS'
                } else if (i1.label === 'B' && i2.label === 'S' && i3.label === 'Z') {
                    label = 'NB'
                } else if (i1.label === 'B' && i2.label === 'S' && i3.label === 'P') {
                    label = 'NB'
                } else if (i1.label === 'B' && i2.label === 'B' && i3.label === 'N') {
                    label = 'Z'
                } else if (i1.label === 'B' && i2.label === 'B' && i3.label === 'Z') {
                    label = 'Z'
                } else if (i1.label === 'B' && i2.label === 'B' && i3.label === 'P') {
                    label = 'Z'
                }
                if (label !== '') {
                    const value = Math.min(
                        i1.getValueY(data.van_toc_hien_tai),
                        i2.getValueY(data.khoang_cach),
                        i3.getValueY(data.van_toc_tuong_doi)
                    )
                    const x = resultPedal.filter((i) => {
                        return i.label === label
                    })
                    if (x.length) {
                        x[0].value = Math.max(x[0].value, value)
                    } else {
                        resultPedal.push({
                            label: label,
                            value: value
                        }
                        )
                    }
                }
            })
        })
    })
    let tuWheelAngle = 0
    const mauWheelAngle = resultAngle.reduce((current, item) => current + item.value, 0)
    if (data.khoang_cach / data.van_toc_hien_tai > 5 && data.van_toc_hien_tai < 15) {
        const outputWheelAngle1 = new OutputWheelAngle1()
        const outputWheelAngle1Available = resultAngle.map((i) => {
            return outputWheelAngle1.arrTrimfTrap.find((item) => item.label === i.label)
        })
        tuWheelAngle = outputWheelAngle1Available.reduce((cur, item, index) => cur + item.getValueX(resultAngle[index].value) * resultAngle[index].value, 0)
    } else {
        const outputWheelAngle2 = new OutputWheelAngle2()
        const outputWheelAngle2Available = resultAngle.map((i) => {
            return outputWheelAngle2.arrTrimfTrap.find((item) => item.label === i.label)
        })
        tuWheelAngle = outputWheelAngle2Available.reduce((current, item, index) => {
            return current + item.getValueX(resultAngle[index].value) * resultAngle[index].value
        }, 0)
    }
    const outputPendalAngle3 = new OutputPendalAngle3()
    const outputPendalAngle3Available = resultPedal.map((i) => {
        return outputPendalAngle3.arrTrimfTrap.find((item) => item.label === i.label)
    })
    const tuPendalAngle = outputPendalAngle3Available.reduce((current, item, index) => {
        return current + item.getValueX(resultPedal[index].value) * resultPedal[index].value
    }, 0)
    const mauPendalAngle = resultPedal.reduce((current, item) => current + item.value, 0)
    return {
        resultWheel: tuWheelAngle / mauWheelAngle,
        resultPedal: tuPendalAngle / mauPendalAngle
    }
}